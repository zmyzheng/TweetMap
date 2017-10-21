package hello;

import io.searchbox.core.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Date;


@Component
public class TwitterStreamHandler {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Value("${key.consumerKey}")
    private String consumerKey;

    @Value("${key.consumerSecret}")
    private String consumerSecret;

    @Value("${key.accessToken}")
    private String accessToken;

    @Value("${key.accessTokenSecret}")
    private String accessTokenSecret;

    private String[] keywords = {"job", "love", "game", "fashion", "trump", "New York","fashion", "food", "lol","hilary", "hello"};

    public void inject()  {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);


        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());

                if(status.getGeoLocation()!=null){
                    System.out.println("hahahhahhahahhahha");
                    String username = status.getUser().getScreenName();
                    String place = status.getUser().getLocation();
                    long tweetId = status.getId();
                    String text = status.getText();
                    double longitude = status.getGeoLocation().getLongitude();
                    double latitude = status.getGeoLocation().getLatitude();
                    String createdAt = status.getCreatedAt().toString();
                    String keyword = null;
                    for(int i = 0; i < keywords.length; i ++){
                        if(text.toLowerCase().indexOf(keywords[i]) != -1){
                            keyword = keywords[i];
                        }
                    }
                    TweetEntity tweetEntity = new TweetEntity(keyword, username, place, longitude, latitude, tweetId, text, createdAt);
                    elasticSearchService.indexDocument(tweetEntity);

                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                //System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {

                //System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        FilterQuery fq = new FilterQuery();

        fq.track(keywords);
        twitterStream.filter(fq);
    }
}
