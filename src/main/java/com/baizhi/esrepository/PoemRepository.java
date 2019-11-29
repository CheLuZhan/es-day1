package com.baizhi.esrepository;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PoemRepository {
    @Autowired
    TransportClient transportClient;

    public List<Map> search(String query) {
        List<Map> list = new ArrayList<>();
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(query)
                .field("name")
                .field("author")
                .field("type")
                .field("content")
                .field("authordes");

        SearchResponse searchResponse = transportClient.prepareSearch("poems")
                .setTypes("poem")
                .setQuery(queryStringQueryBuilder).get();

        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            list.add(sourceAsMap);
        }
        return list;
    }

    public List<Map> highlight(String query) throws UnknownHostException {
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.150.200"), 9300));
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
                if (highlightFields.get(key) != null) {
                    sourceAsMap.put(key, highlightFields.get(key).getFragments()[0].toString());
                }
            }
            list.add(sourceAsMap);
        }
        return list;
    }
}
