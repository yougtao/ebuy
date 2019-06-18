package com.yongtao.ebuy.manager.service;

import com.yongtao.ebuy.manager.pojo.Content;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.DatagridResult;

public interface ContentService
{
    DatagridResult queryList(long categoryId, int page, int rows);

    BuyResult saveContent(Content content);
}
