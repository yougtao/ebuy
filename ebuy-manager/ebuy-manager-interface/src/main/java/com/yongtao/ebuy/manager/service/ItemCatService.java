package com.yongtao.ebuy.manager.service;

import com.yongtao.ebuy.util.pojo.TreeNode;

import java.util.List;

public interface ItemCatService
{
    public List<TreeNode> queryCat(int parentId);
}
