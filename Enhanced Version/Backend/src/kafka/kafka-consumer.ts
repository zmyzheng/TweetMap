import { Client, Consumer } from "kafka-node";

export class KafkaConsumer {
    private readonly consumer: Consumer;

    constructor(connStr: string, clientId: string, topic: string) {
        this.consumer = new Consumer(new Client(connStr, clientId), [{ topic: topic }], {
            autoCommit: true,
            autoCommitIntervalMs: 3000
        });
    }

    bootstrap(action: (msg: any) => void): void {
        this.consumer.on('message', action);
    }
}
