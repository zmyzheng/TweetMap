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



### Enhanced Version
url: http://ec2-52-70-84-118.compute-1.amazonaws.com/

kibana url: http://ec2-52-70-84-118.compute-1.amazonaws.com:5601/

kafka manager url: http://ec2-52-70-84-118.compute-1.amazonaws.com:9000/

zookeeper url: http://ec2-52-70-84-118.compute-1.amazonaws.com:2181/

- **Backend**: Backend services, written in TypeScript with NodeJS and ExpressJS, modular design
- **Frontend**: Frontend views, written in TypeScript with AngularJS v2 and BootStrap


##### Tasks:
Backend:
- [x] Fetch tweets in real time from `Twitter Streaming API`
- [x] Use `kafka` as the Event Queue replacement for AWS SQS
- [x] Gather tweet sentiment data from `watson` from multi-threading worker poll
- [x] Send processed tweets to AWS SNS to notify the persisting procedure
- [x] Use `Elastic Search` to store and index the tweets

Frontend:
- [x] Web UI for user to search for given `keywords` and `username`
- [x] Use `Google Map SDK` to display the tweets in its original location
- [x] Draw sentiment markers (with happy, neutral and sad face)
- [x] Update the tweets list in real time, with sentiment displayed as background
