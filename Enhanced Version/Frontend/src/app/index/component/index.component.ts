import { Component } from '@angular/core';
import { TweetMarker } from '../model/tweet-marker';
import { HttpService } from '../httpservice';
import { TweetEntry } from '../model/tweetentry';
import { Tweet } from '../model/tweet';

@Component({
    selector: 'app-root',
    templateUrl: './index.component.html',
    providers: [HttpService]
})
export class IndexComponent {
    public lat: number = 40.7722314;
    public lng: number = -74.0135349;

    private isRefreshing: boolean;
    public keyword: string = '';
    public user: string = '';

    public tweets: TweetMarker[];
    public entries: TweetEntry[];

    constructor(private httpSvc: HttpService) {
        this.reset();
        setInterval(() => this.refresh(), 3000);
    }

    private clear() {
        this.entries = [];
        this.tweets = [];
    }

    private update(res: Tweet[]) {
        for (let i = res.length - 1; i >= 0; i--) {
            this.tweets.unshift({
                user: res[i].user,
                content: res[i].content,
                time: res[i].time,
                lat: res[i].location.lat,
                lng: res[i].location.lon,
                iconUrl: res[i].sentiment > 0 ? 'assets/happy.png' : res[i].sentiment < 0 ? 'assets/sad.png' : 'assets/neutral.png'
            });

            this.entries.unshift({
                user: res[i].user,
                content: res[i].content,
                time: res[i].time,
                sentiment: res[i].sentiment,
                id: res[i].id
            });
        }
    }

    public reset() {
        if (!this.isRefreshing) {
            this.clear();
            this.isRefreshing = true;
            this.refresh();
        }
    }

    public searchUser() {
        if (this.user.length > 0) {
            this.isRefreshing = false;
            this.clear();
            this.httpSvc.searchByUser(this.user)
                .subscribe(
                    res => {
                        this.update(res);
                    },
                    err => console.log(err)
                );
        }
    }

    public searchContent() {
        if (this.keyword.length > 0) {
            this.isRefreshing = false;
            this.clear();
            this.httpSvc.searchByKeyword(this.keyword)
                .subscribe(
                    res => {
                        this.update(res);
                    },
                    err => console.log(err)
                );
        }
    }

    public refresh() {
        if (this.isRefreshing) {
            this.httpSvc.getAll(this.entries.length > 0 ? this.entries[0].id.toString() : '0')
                .subscribe(
                    res => {
                        this.update(res);
                    },
                    err => console.log(err)
                );
        }
    }
}
