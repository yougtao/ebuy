package com.yongtao.ebuy.manager.service.ipml;

import com.yongtao.ebuy.manager.mapper.ItemMapper;
import com.yongtao.ebuy.manager.pojo.Item;
import com.yongtao.ebuy.manager.pojo.ItemExample;
import com.yongtao.ebuy.manager.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
        if (items!=null && items.size()==1)
            return items.get(0);
        return null;
    }
}
