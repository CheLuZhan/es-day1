import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;


public class TestEasitcSearch {

    @Test
    public void testadd() throws IOException {
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.150.200"), 9300));

        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
        XContentBuilder xContentBuilder1 = xContentBuilder.startObject()
                .field("name", "车路展")
                .field("age", 5)
                .field("content", "这是一位小朋友")
                .field("like", "喜欢让车路展给她买橘子")
                .endObject();

        IndexResponse indexResponse = transportClient.prepareIndex("dangdang", "book", "1").setSource(xContentBuilder1).get();
        System.out.println(indexResponse.status());

    }

    @Test
    public void testupdate() throws IOException {
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.150.200"), 9300));

        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
        XContentBuilder xContentBuilder1 = xContentBuilder.startObject()
                .field("age", 3)
                .endObject();

        UpdateResponse updateRequestBuilder = transportClient.prepareUpdate("dangdang", "book", "2").setDoc(xContentBuilder1).get();
    }

    @Test
    public void testdelete() throws IOException {
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.150.200"), 9300));

        transportClient.prepareDelete("dangdang", "book", "1").get();
    }

    @Test
    public void testselect() throws Exception {
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.150.200"), 9300));
        SearchResponse searchResponse = transportClient.prepareSearch("dangdang")
                .setTypes("book")
                .setQuery(QueryBuilders.matchAllQuery())
                .addSort("age", SortOrder.ASC)
                .setFetchSource("age", null)
                .get();
        SearchHit[] hits1 = searchResponse.getHits().getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            for (Map.Entry<String, Object> entry : sourceAsMap.entrySet()) {
                System.out.println(entry.getKey() + "====>" + entry.getValue());
            }
        }
    }

    //term 查询
    @Test
    public void testterm() throws UnknownHostException {
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.150.200"), 9300));
        transportClient.prepareSearch("dangdang")
                .setTypes("book")
                .setQuery(QueryBuilders.termQuery("naem", "中国"))
                .get();
    }

    //range查询
    @Test
    public void testrnage() throws UnknownHostException {
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.150.200"), 9300));
        transportClient.prepareSearch("dangdang")
                .setTypes("book")
                .setQuery(QueryBuilders.rangeQuery("age").lt(45).gte(8))
                .get();
    }

    //高亮查询
    @Test
    public void highlight(String query) throws UnknownHostException {
   /*     TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.150.200"), 9300));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.requireFieldMatch(false).preTags("<font color='red'>").postTags("</font>").field("*");
        ArrayList<Map> list = new ArrayList<>();
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(query)
                .field("name")
                .field("author")
                .field("type")
                .field("content")
                .field("href")
                .field("authordes");
        SearchResponse searchResponse = transportClient.prepareSearch("poems")
                .setTypes("poem")
                .setQuery(queryStringQueryBuilder)
                .get();
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            for (String key : sourceAsMap.keySet()) {
                if (highlightFields.get(key)!=null){
                    sourceAsMap.put(key,highlightFields.get(key).getFragments()[0].toString());
                }
            }
        }*/
    }

    public static void main(String[] args) {
        System.out.println("qwe");
    }
}
