import { Router } from 'express';
import { EsClient } from '../elasticsearch/esclient';

export const searchRouter: Router = Router();

searchRouter.get('/all', (req, res) => {
    let from = req.param('from');

    EsClient.search('twitter', {
        size: 50,
        sort: [
            { id: { order: 'desc' } }
        ],
        query: {
            range: {
                id: {
                    gt: from
                }
            }
        }
    }).then((r) => {
        res.send(r.hits.hits);
    });
});

searchRouter.get('/user', (req, res) => {
    let key = req.param('key');

    EsClient.search('twitter', {
        query: {
            match: {
                user: key
            }
        },
        sort: [
            { id: { order: 'desc' } }
        ]
    }).then((r) => {
        res.send(r.hits.hits);
    });
});

searchRouter.get('/content', (req, res) => {
    let key = req.param('key');

    EsClient.search('twitter', {
        query: {
            match: {
                content: key
            }
        },
        sort: [
            { id: { order: 'desc' } }
        ]
    }).then((r) => {
        res.send(r.hits.hits);
    });
});

searchRouter.get('/geo', (req, res) => {
    let lat = req.param('lat');
    let lon = req.param('lon');
    let dis = req.param('dis');

    EsClient.search('twitter', {
        query: {
            bool: {
                must: {
                    match_all: {}
                },
                filter: {
                    geo_distance: {
                        distance: dis,
                        location: {
                            lat: Number.parseFloat(lat),
                            lon: Number.parseFloat(lon)
                        }
                    }
                }
            }
        },
        sort: {
            _geo_distance: {
                location: {
                    lat: Number.parseFloat(lat),
                    lon: Number.parseFloat(lon)
                },
                order: 'asc',
                unit: 'km'
            }
        }
    }).then((r) => {
        res.send(r.hits.hits);
    });
});

searchRouter.get('/geo/name', (req, res) => {
    let key = req.param('key');

    EsClient.search('twitter', {
        query: {
            match: {
                locName: key
            }
        },
        sort: [
            { id: { order: 'desc' } }
        ]
    }).then((r) => {
        res.send(r.hits.hits);
    });
});
