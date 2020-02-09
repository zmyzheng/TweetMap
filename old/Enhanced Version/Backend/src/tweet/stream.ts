/*
 curl -XDELETE 'localhost:9200/twitter' && curl -XPUT 'localhost:9200/twitter' -H 'Content-Type: application/json' -d'
 {
 "mappings": {
 "tweet": {
 "properties": {
 "location": {
 "type": "geo_point"
 },
 "locName": {
 "type": "string"
 },
 "id": {
 "type": "long"
 },
 "content": {
 "type": "string"
 },
 "user": {
 "type": "string"
 },
 "time": {
 "type": "string"
 },
 "sentiment": {
 "type": "float"
 }
 }
 }
 }
 }
 '
 */

import * as Twit from 'twit';
import { location } from './location';
import { Tweet, TweetLoc } from "./tweet";
import { KafkaProducer } from "../kafka/kafka-producer";
import { kafkaTopic } from "../config";

export class TwitterStreamRetriever {
    private readonly twit: Twit;
    private readonly kproducer: KafkaProducer;

    constructor(twitConfig: Twit.Options, kConnStr: string, kClientId: string) {
        this.twit = new Twit(twitConfig);
        this.kproducer = new KafkaProducer(kConnStr, kClientId);
    }

    bootstrap() {
        this.twit.stream('statuses/filter', <Twit.Params>{ locations: location.NewYork })
            .on('tweet', (tweet) => {
                if (tweet.coordinates && tweet.coordinates.coordinates && tweet.lang && tweet.lang === 'en') {
                    let t = new Tweet();
                    t.id = tweet.id;
                    t.content = tweet.text;
                    t.user = tweet.user.name;
                    t.time = tweet.created_at;

                    t.location = new TweetLoc();
                    t.location.lon = tweet.coordinates.coordinates[0];
                    t.location.lat = tweet.coordinates.coordinates[1];
                    if (tweet.place) t.locName = tweet.place.full_name;

                    this.kproducer.send(kafkaTopic, JSON.stringify(t));
                }
            });
    }
}
