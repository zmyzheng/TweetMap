
curl -X PUT "https://search-database-yp7n3mmtmp6fybvuhvst6o6bxa.us-west-2.es.amazonaws.com/stream" -d '{
   "index": {
 }}'



curl -X PUT "https://search-database-yp7n3mmtmp6fybvuhvst6o6bxa.us-west-2.es.amazonaws.com/stream/tweets/_mapping" -d '{
   "tweets" : {
   "properties" : {
       "createdAt" : { "type" : "date" },
       "location" : { "type" : "geo_point"},
       "keyword": { "type" : "string" },
       "place": {"type" : "string" },
       "text": {"type" : "string" },
       "tweetId": { "type": "long" },
       "username":{"type" : "string" }
   }}
}'

./mvnw spring-boot:run

./mvnw clean package

java -jar target/twitterservice-1.0-SNAPSHOT.jar