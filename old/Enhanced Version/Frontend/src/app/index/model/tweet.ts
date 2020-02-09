export class TweetLoc {
    lat: number;
    lon: number;
}

export class Tweet {
    id: string;
    location: TweetLoc;
    locName: string;
    content: string;
    user: string;
    time: string;
    sentiment: number;
}
