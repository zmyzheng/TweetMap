import { SNS } from "aws-sdk";
import { awsConfig } from "../config";

export class SNSClient {
    private static sns: SNS = new SNS(awsConfig);

    public static publish(message: string, arn: string): Promise<any> {
        return new Promise<any>((resolve, reject) => {
            this.sns.publish({
                Message: message,
                TopicArn: arn
            }, (err, data) => {
                if (err)
                    reject(err);
                else
                    resolve(data);
            });
        });
    }
}
