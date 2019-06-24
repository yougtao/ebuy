package com.yongtao.ebuy.search.service.dao;


import com.yongtao.ebuy.util.pojo.SearchItem;
import com.yongtao.ebuy.util.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDao
{
    @Autowired
    SolrServer solrServer;

    public SearchResult queryItem(SolrQuery query) {
        SearchResult result = new SearchResult();
        try {
            QueryResponse response = solrServer.query(query);
            SolrDocumentList responseResults = response.getResults();
            result.setRecordCount((int) responseResults.getNumFound());
            List<SearchItem> searchItems = new ArrayList<>();
            // 去除高亮显示的结果
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            for (SolrDocument item : responseResults) {
                SearchItem searchItem = new SearchItem();
                searchItem.setId(item.get("id").toString());
                searchItem.setCategory_name(item.get("item_category_name").toString());
                searchItem.setImage(item.get("item_image").toString());
                searchItem.setPrice(((ArrayList<Long>) item.get("item_price")).get(0));
                searchItem.setSell_point(item.get("item_sell_point").toString());
                searchItem.setTitle(item.get("item_title").toString());
                // 用高亮替换
                if (highlighting != null) {
                    Map<String, List<String>> map = highlighting.get(searchItem.getId());
                    List<String> list = map.get("item_title");
                    if (list != null && list.size() > 0) {
                        searchItem.setTitle(list.get(0));
                    }
                }
                searchItems.add(searchItem);
            }
            result.setItemList(searchItems);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return result;
    }
}
