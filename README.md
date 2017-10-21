# TweetMap


Tweet Map with Trends


This project develop and deploys a web application using AWS Cloud services. The web application would collect Twitts and do some processing and represent the Twitts on GoogleMaps. Following are the required steps:

1. Use Twitter Streaming API (Links to an external site.) to fetch tweets from the twitter hose in real-time. Collect at least one week's worth of tweets.

2. Use AWS (Links to an external site.) ElasticSearch (Links to an external site.) to store the tweets on the back-end.

3. Create a web UI that allows users to search for a few keywords (via a dropdown). The keywords (up to 10) can be of your choosing.

4. Use Google Maps API (Links to an external site.) (or any other mapping library) to render these filtered tweets in the map in whatever manner you want.
Deploy your application on AWS Elastic Beanstalk (Links to an external site.) in an auto-scaling environment.

5. Use ElasticSearch’s (Links to an external site.) geospatial feature that shows tweets that are within a certain distance from the point the user clicks on the map.