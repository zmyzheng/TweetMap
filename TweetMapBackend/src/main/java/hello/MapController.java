package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {

    @Autowired
    ElasticSearchService elasticSearchService;

    @CrossOrigin(origins = "*")
    @RequestMapping("/search")
    public List<TweetEntity> search(@RequestParam(value = "query", defaultValue = "all") String query) {
        List<TweetEntity> tweetEntities = null;

        tweetEntities = elasticSearchService.searchDocument(query);

//        for (TweetEntity tweetEntity : tweetEntities) {
//            System.out.println(tweetEntity.getText());
//        }
        return tweetEntities;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/distanceFilter")
    public List<TweetEntity> distanceFilter(@RequestParam(value = "distance", defaultValue = "50000") String distance,
                                            @RequestParam(value = "lat", defaultValue = "50.0") String lat,
                                            @RequestParam(value = "lon", defaultValue = "9.0") String lon) {

        double dist = Double.parseDouble(distance);
        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(lon);
        System.out.println(dist);
        System.out.println(latitude);
        System.out.println(longitude);

        List<TweetEntity> tweetEntities = null;

        tweetEntities = elasticSearchService.searchWithInDistance(latitude, longitude, dist);

        for (TweetEntity tweetEntity : tweetEntities) {
            System.out.println(tweetEntity.getText());
        }
        return tweetEntities;
    }
}
