package com.yongtao.ebuy.search.controller;


import com.yongtao.ebuy.search.service.SearchService;
import com.yongtao.ebuy.util.pojo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


@Controller
public class SearchController
{
    @Autowired
    SearchService searchService;


    @RequestMapping("/search")
    public String search(@RequestParam(defaultValue = "三星") String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "60") int rows, Model model) {
        try {
            keyword = new String(keyword.getBytes("iso8859-1"), StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //keyword = "三星";
        SearchResult result = searchService.searchItem(page, rows, keyword);
        model.addAttribute("query", keyword);
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("recordCount", result.getRecordCount());
        model.addAttribute("itemList", result.getItemList());
        return "search";
    }

}
