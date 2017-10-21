package hello;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.params.Parameters;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ElasticSearchService {

    @Value("${es.index}")
    private String INDEX;

    @Value("${es.type}")
    private String TYPE;

    private JestClient client = null;

    private static long docId = System.currentTimeMillis();


    @Autowired
    public ElasticSearchService(@Value("${es.endpoint}") String endPoint) {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(endPoint)
                .multiThreaded(true)
                .build());
        client = factory.getObject();
    }

    public void indexDocument(TweetEntity tweetEntity) {
        Index index = new Index.Builder(tweetEntity).index(INDEX)
                .type(TYPE)//.id(docId + "")
                .build();
        try {

            client.execute(index);
            docId++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TweetEntity> searchDocument(String key) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (key.equals("all")) {
            searchSourceBuilder.query(QueryBuilders. matchAllQuery());
        }
        else {
            searchSourceBuilder.query(QueryBuilders.matchQuery("keyword", key));
        }
        Search search = new Search.Builder(searchSourceBuilder.toString()).setParameter(Parameters.SIZE, 100)
                .addIndex(INDEX).addType(TYPE).build();
        List<TweetEntity> tweetEntities = null;
        try {
            JestResult result = client.execute(search);
            tweetEntities = result.getSourceAsObjectList(TweetEntity.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweetEntities;
    }

    public List<TweetEntity> searchWithInDistance(double lat, double lon, double distance) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(distance, DistanceUnit.KILOMETERS));

        Search search = new Search.Builder(searchSourceBuilder.toString()).setParameter(Parameters.SIZE, 100)
                .addIndex(INDEX).addType(TYPE).build();
        List<TweetEntity> tweetEntities = null;
        try {
            JestResult result = client.execute(search);
            tweetEntities = result.getSourceAsObjectList(TweetEntity.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweetEntities;
    }

}
