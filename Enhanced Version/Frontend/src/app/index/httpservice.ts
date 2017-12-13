import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/toArray';

@Injectable()
export class HttpService {
    private urlBase = 'http://ec2-52-70-84-118.compute-1.amazonaws.com';

    constructor(private http: Http) {
    }

    private get(url: string) {
        return this.http.get(this.urlBase + url)
            .flatMap(res => res.json())
            .map(r => (<any>r)._source)
            .toArray();
    }

    public getAll(from: string): Observable<any> {
        return this.get('/search/all?from=' + from);
    }

    public searchByKeyword(keyword: string): Observable<any> {
        return this.get('/search/content?key=' + keyword);
    }

    public searchByUser(user: string): Observable<any> {
        return this.get('/search/user?key=' + user);
    }
}
