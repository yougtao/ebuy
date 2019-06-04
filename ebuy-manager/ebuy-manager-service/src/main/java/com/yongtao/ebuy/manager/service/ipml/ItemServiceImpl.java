package com.yongtao.ebuy.manager.service.ipml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yongtao.ebuy.manager.mapper.ItemMapper;
import com.yongtao.ebuy.manager.pojo.Item;
import com.yongtao.ebuy.manager.pojo.ItemExample;
import com.yongtao.ebuy.manager.service.ItemService;
import com.yongtao.ebuy.util.pojo.DatagridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService
{
    @Autowired
    private ItemMapper itemMapper;


    @Override
    public Item queryById(long id) {
        ItemExample example = new ItemExample();
        ItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<Item> items = itemMapper.selectByExample(example);
        if (items != null && items.size() == 1)
            return items.get(0);
        return null;
    }

    @Override
    public DatagridResult queryByPage(int page, int rows) {
        DatagridResult result = new DatagridResult();

        PageHelper.startPage(page, rows);
        ItemExample example = new ItemExample();
        List<Item> items = itemMapper.selectByExample(example);
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        result.setTotal(pageInfo.getTotal());
        result.setRows(items);
        return result;
    }
}
