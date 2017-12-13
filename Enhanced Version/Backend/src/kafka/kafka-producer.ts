import { Client, Producer } from "kafka-node";

export class KafkaProducer {
    private readonly producer: Producer;

    constructor(connString: string, clientId: string) {
        this.producer = new Producer(new Client(connString, clientId));
    }

    send(topic: string, message: string): Promise<any> {
        return new Promise<any>((resolve, reject) => {
            this.producer.send([{
                topic: topic,
                messages: message
            }], (err, res) => {
                if (err) reject(err);
                else resolve(res);
            });
        });
    }
}
