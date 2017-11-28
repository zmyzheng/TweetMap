# TweetMap


Tweet Map with Trends


This project develop and deploys a web application using AWS Cloud services. The web application would collect Twitts and do some processing and represent the Twitts on GoogleMaps. Following are the required steps:

1. Use Twitter Streaming API to fetch tweets from the twitter hose in real-time. 

2. Use AWS ElasticSearch to store the tweets on the back-end.

3. Use Angular to create a web UI that allows users to search for a few keywords (via a dropdown). The keywords (up to 10) can be of your choosing.

4. Use Google Maps API to render these filtered tweets in the map in whatever manner you want.

5. Use Spring boot to build a RESTful server and deploy the application on AWS Elastic Beanstalk in an auto-scaling environment.

5. Use ElasticSearch’s geospatial feature that shows tweets that are within a certain distance from the point the user clicks on the map.


run the program:

1. build frontend

$ cd TweetMapFrontend

$ ng build 

2. copy files in target into TweetMapBackend/src/main/resources/static/

3. ./mvnw clean package

4. java -jar target/twitterservice-1.0-SNAPSHOT.jar