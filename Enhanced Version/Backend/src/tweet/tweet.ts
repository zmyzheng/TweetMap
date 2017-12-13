export class TweetLoc {
    lat: number;
    lon: number;
}

export class Tweet {
    id: number;
    location: TweetLoc;
    locName: string;
    content: string;
    user: string;
    time: string;
    sentiment: number;
}
