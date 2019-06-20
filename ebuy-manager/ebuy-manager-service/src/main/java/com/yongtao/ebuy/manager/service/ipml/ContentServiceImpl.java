package com.yongtao.ebuy.manager.service.ipml;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.dubbo.common.json.JSON;
import com.yongtao.ebuy.manager.mapper.ContentExMapper;
import com.yongtao.ebuy.manager.mapper.ContentMapper;
import com.yongtao.ebuy.manager.pojo.Content;
import com.yongtao.ebuy.manager.pojo.ContentExample;
import com.yongtao.ebuy.manager.service.ContentService;
import com.yongtao.ebuy.util.jedis.JedisClient;
import com.yongtao.ebuy.util.json.JsonUtil;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.DatagridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService
{
    @Autowired
    ContentMapper contentMapper;

    @Autowired
    ContentExMapper contentExMapper;

    @Autowired
    JedisClient JedisClient;


    @Override
    public BuyResult editContent(Content content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.updateByPrimaryKey(content);

        // 清理redis缓存
        String key = "CONTENT:" + content.getCategoryId();
        JedisClient.del(key);
        return BuyResult.ok();
    }

    @Override
    public DatagridResult queryList(long categoryId, int page, int rows) {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryId", categoryId);
        map.put("start", (page - 1) * rows);
        map.put("size", rows);

        DatagridResult result = new DatagridResult();
        List list = contentExMapper.queryByPage(map);
        int total = contentExMapper.queryCount(map);
        result.setRows(list);
        result.setTotal(total);
        return result;
    }

    @Override
    public BuyResult saveContent(Content content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        // 清理redis缓存
        String key = "CONTENT:" + content.getCategoryId();
        JedisClient.del(key);
        return BuyResult.ok();
    }


    @Override
    public List<Content> queryByCategoryId(long categoryId) {
        String key = "CONTENT:" + categoryId;
        String json = JedisClient.get(key);
        if (StringUtils.isNoneBlank(json)) {
            // 查到, 将json转换为集合并返回
            try {
                return JsonUtil.jsonToList(json, Content.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ContentExample example = new ContentExample();
        ContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<Content> list = contentMapper.selectByExample(example);

        // 添加到redis缓存
        try {
            JedisClient.set(key, JsonUtil.toJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
