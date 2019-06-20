package com.yongtao.ebuy.manager.controller;

import com.yongtao.ebuy.manager.pojo.Content;
import com.yongtao.ebuy.manager.service.ContentService;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.DatagridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ContentController
{
    @Autowired
    ContentService contentService;


    @ResponseBody
    @RequestMapping("/content/query/list")
    public DatagridResult queryList(long categoryId, int page, int rows) {
        return contentService.queryList(categoryId, page, rows);
    }


    @ResponseBody
    @RequestMapping("/content/save")
    public BuyResult saveContent(Content content) {
        return contentService.saveContent(content);
    }


    @ResponseBody
    @RequestMapping("/rest/content/edit")
    public BuyResult editContent(Content content){
        return contentService.editContent(content);
    }


    // 测试
    @ResponseBody
    @RequestMapping("/content/query")
    public BuyResult queryByCategoryId() {
        BuyResult result = BuyResult.ok();
        result.setData(contentService.queryByCategoryId(109));
        return result;
    }
}
