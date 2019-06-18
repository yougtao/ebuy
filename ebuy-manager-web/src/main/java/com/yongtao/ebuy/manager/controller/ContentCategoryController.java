package com.yongtao.ebuy.manager.controller;

import com.yongtao.ebuy.manager.service.ContentCategoryService;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController
{
    @Autowired
    ContentCategoryService contentCategoryService;


    @ResponseBody
    @RequestMapping("/content/category/list")
    public List<TreeNode> queryContent(@RequestParam(defaultValue = "0") long id) {
        return contentCategoryService.queryCat(id);
    }


    @ResponseBody
    @RequestMapping("/content/category/create")
    public BuyResult createCat(int parentId, String name) {
        return contentCategoryService.saveCat(parentId, name);
    }


    @ResponseBody
    @RequestMapping("/content/category/update")
    public BuyResult updateCat(long id, String name) {
        return contentCategoryService.updateCat(id, name);
    }

    @ResponseBody
    @RequestMapping("/content/category/delete")
    public BuyResult deleteCat(long id) {
        return contentCategoryService.deleteCat(id);
    }

}
