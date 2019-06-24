import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SolrjTest
{
    // solrj添加假
    //@Test
    public void testAddDoc() {
        SolrServer solrServer = new HttpSolrServer("http://192.168.22.139:8080/solr/new_core");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", 45687);
        document.addField("item_title", "蔡英文造谣“国民党都想花郭台铭的钱”？吴敦义怒");
        try {
            solrServer.add(document);
            solrServer.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }


    // 删除文档
    //@Test
    public void testDeleteByIdDoc() {
        SolrServer solrServer = new HttpSolrServer("http://192.168.22.139:8080/solr/new_core");
        String query = "中纪检湖南爱立信";
        try {
            solrServer.deleteByQuery("item_title:"+query);
            solrServer.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    // 查询
    //@Test
    public void testQueryById() {
        SolrServer solrServer = new HttpSolrServer("http://192.168.22.139:8080/solr/new_core");
        SolrQuery query = new SolrQuery();
        query.setQuery("item_title:爱美");
        try {
            QueryResponse response = solrServer.query(query);
            SolrDocumentList results = response.getResults();
            System.out.println("total:" + results.getNumFound());
            for (SolrDocument doc : results) {
                System.out.println("id:" + doc.get("id") + " ,item_title: " + doc.get("item_title"));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

    // 高亮查询
    //@Test
    public void testQueryDocLint() {
        SolrServer solrServer = new HttpSolrServer("http://192.168.22.139:8080/solr/new_core");
        SolrQuery query = new SolrQuery();
        query.setQuery("item_title:美国");
        query.setHighlight(true);
        // 设置搜索域
        query.set("df", "item_title");
        // 设置高亮显示的前缀和后缀
        query.setHighlightSimplePre("<em style='color:red'>");
        query.setHighlightSimplePost("</em>");

        // 查询数据
        try {
            QueryResponse response = solrServer.query(query);
            SolrDocumentList results = response.getResults();

            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            System.out.println("total: " + results.getNumFound());
            for (SolrDocument doc : results) {
                System.out.println("id: " + doc.get("id") + " ,item_title: " + doc.get("item_title"));
            }
            System.out.println("-----高亮显示结果--------");
            for (SolrDocument doc : results) {
                String id = doc.get("id").toString();
                String itemTitle = doc.get("item_title").toString();
                Map<String, List<String>> map = highlighting.get(id);
                List<String> list = map.get("item_title");
                if (list != null && list.size() > 0) {
                    itemTitle = list.get(0);
                }
                System.out.println("id: " + id + " ,item_title: " + itemTitle);
            }

        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
}
