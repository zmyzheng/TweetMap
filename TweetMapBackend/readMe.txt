curl -X PUT "https://search-database-yp7n3mmtmp6fybvuhvst6o6bxa.us-west-2.es.amazonaws.com/twitters/twitterEntities/_mapping" -d '{
   "twitterEntities" : {
   "properties" : {
       "createdAt" : { "type" : "string" },
       "location" : { "type" : "geo_point"},
       "keyword": { "type" : "string" },
       "place": {"type" : "string" },
       "text": {"type" : "string" },
       "tweetId": { "type": "long" },
       "username":{"type" : "string" }
   }}
}'


curl -X PUT "https://search-database-yp7n3mmtmp6fybvuhvst6o6bxa.us-west-2.es.amazonaws.com/twitters" -d '{
   "index": {
 }}'


 -------------------
 curl -X PUT "https://search-daybvuhvst6o6bxa.us-west-2.es.amazonaws.com/twitters" -d '{
 >    "index": {
 >  }}'
 {"acknowledged":true,"shards_acknowledged":true}Zmy-Apple@MingyangdeMacBook-Pro:~$ cybvuhvst6o6bxa.us-west-2.es.amazonaws.com/twitters/twitterEntities/_mapping" -d '{
    "twitterEntities" : {
    "properties" : {
        "createdAt" : { "type" : "string" },
        "location" : { "type" : "geo_point"},
        "keyword": { "type" : "string" },
        "place": {"type" : "string" },
        "text": {"type" : "string" },
        "tweetId": { "type": "long" },
        "username":{"type" : "string" }
    }}
 }'
 {"acknowledged":true}Zmy-Apple@MingyangdeMacBook-Pro:~$