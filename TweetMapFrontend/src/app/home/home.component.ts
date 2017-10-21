import { Component, OnInit } from '@angular/core';
import {TweetEntity} from "../shared/tweet-entity";
import {Marker} from "../shared/marker";
import {TweetService} from "../services/tweet.service";
import { MouseEvent as AGMMouseEvent } from '@agm/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  lat: number = 51.678418;
  lng: number = 7.809007;
  marker: Marker;
  tweetEntities: TweetEntity[] = [];
  keyWords: String[] = ["job", "love", "game", "fashion", "trump", "New York","fashion", "food", "lol","hilary", "hello"];
  distance: number = 500;


  constructor(private tweetService: TweetService) { }

  async ngOnInit(): Promise<any> {
    try {
      let result = await this.tweetService.getTweetsByKeyword('all');
      this.tweetEntities = result;
      console.log('get Tweets By Keyword success');
      console.log(this.tweetEntities.length);
    } catch (ex) {
      console.error('An error occurred', ex);
    }
  }
  async getTweets(keyword: string) {
    try {
      let result = await this.tweetService.getTweetsByKeyword(keyword);
      this.tweetEntities = result;
      console.log('get Tweets By Keyword success');
    } catch (ex) {
      console.error('An error occurred', ex);
    }
  }

  async filterDistance() {
    try {
      let result = await this.tweetService.searchWithinDistance(this.lat, this.lng, this.distance);
      this.tweetEntities = result;
      console.log('get Tweets By Keyword success');
    } catch (ex) {
      console.error('An error occurred', ex);
    }
  }

  clickedMarker(label: string, index: number) {
    console.log(`clicked the marker: ${label || index}`)
  }

  mapClicked($event: AGMMouseEvent) {
    this.lat = $event.coords.lat;
    this.lng = $event.coords.lng;
  }



}
