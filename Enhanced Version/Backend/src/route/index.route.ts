import { Router } from 'express';

export const indexRouter = Router();

indexRouter.get('/', (req, res) => {
    res.send('Hello!');
});
