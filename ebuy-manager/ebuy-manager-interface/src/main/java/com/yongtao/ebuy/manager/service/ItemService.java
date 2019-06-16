package com.yongtao.ebuy.manager.service;

import com.yongtao.ebuy.manager.pojo.Item;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.DatagridResult;


public interface ItemService
{

    // 查询
    Item queryById(long id);


    // 分页查询
    DatagridResult queryByPage(int page, int rows);


    // 保存
    BuyResult insertItem(Item item, String desc);

}
