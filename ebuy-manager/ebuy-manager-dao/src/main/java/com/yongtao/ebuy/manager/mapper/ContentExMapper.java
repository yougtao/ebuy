package com.yongtao.ebuy.manager.mapper;

import com.yongtao.ebuy.manager.pojo.Content;

import java.util.List;
import java.util.Map;

public interface ContentExMapper
{
    List<Content> queryByPage(Map page);

    int queryCount(Map page);
}
