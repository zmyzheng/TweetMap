import {Geo} from "./geo";

export class TweetEntity {
  keyword: string;
  username: string;
  place: string;
  // longitude: number;
  // latitude: number;
  location: Geo;
  tweetId: number;
  text: string;
  createdAt: Date;


}
