package com.baizhi.test;

import com.baizhi.SpringBootApp;
import com.baizhi.dao.PoemDAO;
import com.baizhi.entity.Poem;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = SpringBootApp.class)
@RunWith(SpringRunner.class)
public class Testes {
    @Autowired
    TransportClient transportClient;

    @Autowired
    PoemDAO poemDAO;

    //添加
    @Test
    public void testAdd() throws IOException {
        List<Poem> poems = poemDAO.selectAll();
        for (Poem poem : poems) {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
            xContentBuilder.startObject()
                    .field("name", poem.getName())
                    .field("author", poem.getAuthor())
                    .field("type", poem.getType())
                    .field("href", poem.getHref())
                    .field("authordes", poem.getAuthordes())
                    .endObject();
            transportClient.prepareIndex("poems", "poem").setSource(xContentBuilder).get();

        }
    }
}
