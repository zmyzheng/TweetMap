import { Router } from 'express';
import { EsClient } from "../elasticsearch/esclient";

export const tweetRouter = Router();

tweetRouter.post('/', (req, res) => {
    let t = JSON.parse(req.body.Message);
    EsClient.create('twitter', 'tweet', t.id.toString(), t)
        .then(() => res.send('OK'));
});
