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
    public final String GAME_ID = "game_id";
    public final String GAMES = "games";
    public final String GAME_INFO_QUEUE = "game_info";
    private final RestHighLevelClient client;
    public GameInfoListener(RestHighLevelClient cli) {
       this.client = cli;
    }

    @RabbitListener(queues =  GAME_INFO_QUEUE)
    public void onMessage(String msg, @Header(GAME_ID) String id) throws IOException {
        IndexRequest index = new IndexRequest(GAMES);
        index.id(id);
        index.source(msg, XContentType.JSON);
        this.client.index(index, RequestOptions.DEFAULT);
    }
}
