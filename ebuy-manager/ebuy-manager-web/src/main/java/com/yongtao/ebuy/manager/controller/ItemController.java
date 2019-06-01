package com.yongtao.ebuy.manager.controller;


import com.yongtao.ebuy.manager.pojo.Item;
import com.yongtao.ebuy.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController
{
    @Autowired
    private ItemService itemService;


    @ResponseBody
    @RequestMapping("/item/{id}")
    public Item queryById(@PathVariable long id) {
        return itemService.queryById(id);
    }
}
