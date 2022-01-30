package fr.lernejo.search.api;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GameInfoListener {
    private final RestHighLevelClient client;
    public GameInfoListener(RestHighLevelClient cli) {
       this.client = cli;
    }

    @RabbitListener(queues =  AmqpConfiguration.GAME_INFO_QUEUE)
    public void onMessage(String msg, @Header("game_id") String id) throws IOException {
        IndexRequest index = new IndexRequest("games");
        index.id(id);
        index.source(msg, XContentType.JSON);
        System.out.println(id);
        this.client.index(index, RequestOptions.DEFAULT);
    }
}
