package com.yongtao.ebuy.search.service.impl;

import com.yongtao.ebuy.search.mapper.SearchItemMapper;
import com.yongtao.ebuy.search.service.SearchService;
import com.yongtao.ebuy.search.service.dao.SearchDao;
import com.yongtao.ebuy.util.pojo.BuyResult;
import com.yongtao.ebuy.util.pojo.SearchItem;
import com.yongtao.ebuy.util.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SearchServiceImpl implements SearchService
{
    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private SearchDao searchDao;


    @Override
    public BuyResult importIndex() {
        List<SearchItem> list = searchItemMapper.getItemList();
        List<SolrInputDocument> documents = new ArrayList<>();
        // 遍历list 将数据导入索引库
        for (SearchItem item : list) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", item.getId());
            doc.addField("item_title", item.getTitle());
            doc.addField("item_sell_point", item.getSell_point() != null ? item.getSell_point() : "");
            doc.addField("item_price", item.getPrice());
            doc.addField("item_category_name", item.getCategory_name());
            // 处理图片地址
            String image = item.getImage();
            String[] images = image.split(",");
            if (images.length > 0) {
                image = images[0];
            }
            doc.addField("item_image", image);

            documents.add(doc);
        }
        try {
            solrServer.add(documents);
            solrServer.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return BuyResult.ok();
    }


    @Override
    public SearchResult   (int page, int rows, String keyword) {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("df", "item_title");
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");
        // 设置查询条件
        solrQuery.setQuery("item_title:" + keyword);
        SearchResult result = searchDao.queryItem(solrQuery);
        return result;
    }
}// end
