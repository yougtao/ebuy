package com.yongtao.ebuy.manager.service.ipml;


import com.yongtao.ebuy.manager.mapper.ItemCatMapper;
import com.yongtao.ebuy.manager.pojo.ItemCat;
import com.yongtao.ebuy.manager.pojo.ItemCatExample;
import com.yongtao.ebuy.manager.service.ItemCatService;
import com.yongtao.ebuy.util.pojo.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService
{
    @Autowired
    ItemCatMapper itemCatMapper;


    public List<TreeNode> queryCat(int parentId) {
        ItemCatExample example = new ItemCatExample();
        ItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo((long) parentId);
        List<ItemCat> itemCats = itemCatMapper.selectByExample(example);

        List<TreeNode> nodes = new ArrayList<>();
        for (ItemCat itemCat : itemCats) {
            TreeNode node = new TreeNode();
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            node.setState(itemCat.getIsParent() ? "closed" : "open");
            nodes.add(node);
        }
        return nodes;
    }

}
