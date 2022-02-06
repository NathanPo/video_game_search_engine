package fr.lernejo.search.api;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class ElasticSearchController {
    private final RestHighLevelClient client;

    public ElasticSearchController(RestHighLevelClient client) {
        this.client = client;
    }

    @GetMapping("/api/games")
    ArrayList<Object> getGames(@RequestParam(name = "query") String query) throws IOException {
        ArrayList res = new ArrayList();
        SearchRequest rqst = new SearchRequest()
                .source(SearchSourceBuilder.searchSource()
                .query(new QueryStringQueryBuilder(query)));
        this.client.search(rqst, RequestOptions.DEFAULT)
            .getHits()
            .forEach(hit -> res.add(hit.getSourceAsMap()));
        return res;
    }
}
