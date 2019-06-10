package com.yongtao.ebuy.manager.controller;

import com.yongtao.ebuy.manager.service.ItemCatService;
import com.yongtao.ebuy.util.pojo.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class ItemCatController
{
    @Autowired
    ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping("/item/cat/list")
    public List<TreeNode> queryCat(@RequestParam(defaultValue = "0", name = "id") Integer parentId) {
        return itemCatService.queryCat(parentId);
    }
}
