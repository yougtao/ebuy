package com.yongtao.ebuy.manager.service.ipml;

import com.yongtao.ebuy.manager.mapper.ContentCategoryExMapper;
import com.yongtao.ebuy.manager.mapper.ContentCategoryMapper;
import com.yongtao.ebuy.manager.pojo.ContentCategory;
import com.yongtao.ebuy.manager.pojo.ContentCategoryExample;
import com.yongtao.ebuy.manager.service.ContentCategoryService;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ContentCategoryServiceImpl implements ContentCategoryService
{
    @Autowired
    ContentCategoryMapper contentCategoryMapper;

    @Autowired
    ContentCategoryExMapper contentCategoryExMapper;


    @Override
    public List<TreeNode> queryCat(long id) {
        ContentCategoryExample example = new ContentCategoryExample();
        ContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<ContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        for (ContentCategory cat : list) {
            TreeNode node = new TreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent() ? "closed" : "open");
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public BuyResult saveCat(int parentId, String name) {
        ContentCategory category = new ContentCategory();
        category.setCreated(new Date());
        category.setUpdated(new Date());
        category.setIsParent(false);
        category.setName(name);
        category.setParentId((long) parentId);
        category.setSortOrder(1);
        category.setStatus(1);
        contentCategoryExMapper.insert(category);

        // 将其父节点的idParent字段设为1
        ContentCategory parent = contentCategoryMapper.selectByPrimaryKey((long) parentId);
        parent.setIsParent(true);
        contentCategoryMapper.updateByPrimaryKey(parent);

        return new BuyResult(200, category);
    }

    @Override
    public BuyResult updateCat(long id, String name) {
        ContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
        category.setName(name);
        contentCategoryMapper.updateByPrimaryKey(category);
        return BuyResult.ok();
    }

    @Override
    public BuyResult deleteCat(long id) {
        ContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
        category.setStatus(2);
        contentCategoryMapper.updateByPrimaryKey(category);

        ContentCategoryExample example = new ContentCategoryExample();
        ContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(category.getParentId()).andStatusEqualTo(1);
        List<ContentCategory> list = contentCategoryMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            ContentCategory parent = contentCategoryMapper.selectByPrimaryKey(category.getParentId());
            parent.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        return BuyResult.ok();
    }
}