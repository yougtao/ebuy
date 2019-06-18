package com.yongtao.ebuy.manager.service;

import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.TreeNode;

import java.util.List;

public interface ContentCategoryService
{
    List<TreeNode> queryCat(long id);

    BuyResult saveCat(int parentId, String name);

    BuyResult updateCat(long id, String name);

    BuyResult deleteCat(long id);
}
