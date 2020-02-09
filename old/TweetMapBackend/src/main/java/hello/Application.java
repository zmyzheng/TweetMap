package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import twitter4j.TwitterException;

import java.util.List;

@SpringBootApplication
public class Application {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        TwitterStreamHandler twitterStreamHandler =
//                context.getBean("twitterStreamHandler", TwitterStreamHandler.class);
//
//        twitterStreamHandler.inject();



//        ElasticSearchService elasticSearchService =
//                context.getBean("elasticSearchService", ElasticSearchService.class);
//
//        List<TweetEntity> tweetEntities = elasticSearchService.searchDocument("love");
//
//        for (TweetEntity tweetEntity : tweetEntities) {
//            System.out.println(tweetEntity.getText());
//        }

    }
}
