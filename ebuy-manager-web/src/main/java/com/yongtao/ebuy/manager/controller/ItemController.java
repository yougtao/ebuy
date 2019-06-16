package com.yongtao.ebuy.manager.controller;


import com.yongtao.ebuy.manager.pojo.Item;
import com.yongtao.ebuy.manager.service.ItemService;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.DatagridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ItemController
{
    @Autowired
    private ItemService itemService;


    @ResponseBody
    @RequestMapping("/item/save")
    public BuyResult saveItem(Item item, String desc) {
        return itemService.insertItem(item, desc);
    }


    @ResponseBody
    @RequestMapping("/item/list")
    public DatagridResult itemList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows) {
        System.out.println("test");
        return itemService.queryByPage(page, rows);
    }


    @ResponseBody
    @RequestMapping("/item/{id}")
    public Item queryById(@PathVariable long id) {
        return itemService.queryById(id);
    }


}
