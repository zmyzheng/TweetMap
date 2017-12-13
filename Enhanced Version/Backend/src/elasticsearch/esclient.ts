import { Client, ConfigOptions, CreateDocumentResponse, SearchResponse, SearchParams } from 'elasticsearch';
import { esConfig } from "../config";

export class EsClient {
    private static esClient: Client = new Client(esConfig);

    public static create(index: string, type: string, id: string, body: any): Promise<CreateDocumentResponse> {
        return new Promise<CreateDocumentResponse>((resolve, reject) => {
            this.esClient.create({
                index: index,
                type: type,
                id: id,
                body: body
            }, (err, res) => {
                if (err) reject(err);
                else resolve(res);
            });
        });
    }

    public static search<T>(index: string, body: any): Promise<SearchResponse<T>> {
        return new Promise<SearchResponse<T>>((resolve, reject) => {
            this.esClient.search({
                index: index,
                body: body
            }, (err, res) => {
                if (err) reject(err);
                else resolve(<SearchResponse<T>>res);
            })
        });
    }
}
