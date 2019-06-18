package com.yongtao.ebuy.manager.service.ipml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yongtao.ebuy.manager.mapper.ContentExMapper;
import com.yongtao.ebuy.manager.mapper.ContentMapper;
import com.yongtao.ebuy.manager.pojo.Content;
import com.yongtao.ebuy.manager.pojo.ContentExample;
import com.yongtao.ebuy.manager.service.ContentService;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.DatagridResult;
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


    @Override
    public DatagridResult queryList(long categoryId, int page, int rows) {
        Map map = new HashMap<>();
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
        return BuyResult.ok();
    }
}
