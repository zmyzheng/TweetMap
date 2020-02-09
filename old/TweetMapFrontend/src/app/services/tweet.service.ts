import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {backendServerURL} from '../shared/config';

@Injectable()
export class TweetService {

  constructor(private http: Http) { }

  async getTweetsByKeyword(keyword: String): Promise<any> {
    console.log('get Tweets By Keyword');
    let url =  backendServerURL + '/search?query=' + keyword;
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    try {
      let res = await this.http.get(url, options).toPromise();
      console.log(res.json());
      return res.json();
    } catch (ex) {
      console.log(ex);
    }
  }

  async searchWithinDistance(lat:number, lng: number, distance: number): Promise<any> {
    console.log('search Within Distance');
    let url =  backendServerURL + '/distanceFilter?lat=' + lat + '&' + 'lon=' + lng + '&' + 'distance=' + distance;
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    try {
      let res = await this.http.get(url, options).toPromise();
      console.log(res.json());
      return res.json();
    } catch (ex) {
      console.log(ex);
    }
  }

}
