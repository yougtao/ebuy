package com.yongtao.ebuy.manager.service;

import com.yongtao.ebuy.manager.pojo.Content;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.DatagridResult;

import java.util.List;

public interface ContentService
{
    DatagridResult queryList(long categoryId, int page, int rows);

    BuyResult saveContent(Content content);

    List<Content> queryByCategoryId(long categoryId);

    BuyResult editContent(Content content);
}
