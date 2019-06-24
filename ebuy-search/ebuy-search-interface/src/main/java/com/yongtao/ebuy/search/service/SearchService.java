package com.yongtao.ebuy.search.service;

import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.SearchResult;

public interface SearchService
{
    BuyResult importIndex();

    SearchResult searchItem(int page, int rows, String keyword);
}
