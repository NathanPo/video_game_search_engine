package fr.lernejo.search.api;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfiguration {

    @Bean
    public RestHighLevelClient client (@Value("${elasticsearch.host:localhost}") String address,
                                        @Value("${elasticsearch.port:9200}") int port,
                                        @Value("${elasticsearch.username:elastic}") String username,
                                        @Value("${elasticsearch.password:admin}") String pswd){
        final CredentialsProvider credentials = new BasicCredentialsProvider();
        credentials.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, pswd));
        RestClientBuilder builder = RestClient.builder(new HttpHost(address, port))
            .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentials));
        return new RestHighLevelClient(builder);
    }
}
