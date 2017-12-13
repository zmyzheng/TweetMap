import { TweetWorker } from "./tweet-worker";
import { kafkaClientId, kafkaConnStr, kafkaTopic, watsonConfig } from "../config";

export class WorkerPool {
    private readonly pool: TweetWorker[];

    constructor(num: number) {
        this.pool = [];
        for (let i = 0; i < num; i++) {
            this.pool[i] = new TweetWorker(kafkaConnStr, kafkaClientId, kafkaTopic, watsonConfig);
        }
    }
}
