import { Worker } from "webworker-threads";
import { KafkaConsumer } from "../kafka/kafka-consumer";
import { SentimentAnalyzer } from "../watson/sentiment-analyzer";
import { SNSClient } from "../sns/snsclient";
import { snsArn } from "../config";

export class TweetWorker {
    private readonly kconsumer: KafkaConsumer;

    constructor(kConnStr: string, kClientId: string, kTopic: string, wConfig: any[]) {
        this.kconsumer = new KafkaConsumer(kConnStr, kClientId, kTopic);
        this.kconsumer.bootstrap(this.perform);
    }

    private perform(msg: any): void {
        let t = JSON.parse(msg.value);
        t.sentiment = SentimentAnalyzer.analyze(t.content)
            .then((s) => {
                t.sentiment = s;
                SNSClient.publish(JSON.stringify(t), snsArn);
            })
            .catch((e) => {
                t.sentiment = 0;
                SNSClient.publish(JSON.stringify(t), snsArn);
            });
    }
}
