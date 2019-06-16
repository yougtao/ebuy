package com.yongtao.ebuy.manager.service.ipml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yongtao.ebuy.manager.mapper.ItemDescMapper;
import com.yongtao.ebuy.manager.mapper.ItemMapper;
import com.yongtao.ebuy.manager.pojo.Item;
import com.yongtao.ebuy.manager.pojo.ItemDesc;
import com.yongtao.ebuy.manager.pojo.ItemExample;
import com.yongtao.ebuy.manager.service.ItemService;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.DatagridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ItemServiceImpl implements ItemService
{
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;


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


    @Override
    public BuyResult insertItem(Item item, String desc) {
        String idStr = System.currentTimeMillis() + "" + new Random().nextInt() % 100;
        long id = Long.parseLong(idStr);

        item.setId(id);
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDesc.setItemDesc(desc);
        itemDescMapper.insert(itemDesc);

        return BuyResult.ok();
    }
} // end class