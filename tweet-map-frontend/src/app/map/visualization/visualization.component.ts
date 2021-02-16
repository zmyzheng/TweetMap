import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';


@Component({
  selector: 'app-visualization',
  templateUrl: './visualization.component.html',
  styleUrls: ['./visualization.component.css']
})
export class VisualizationComponent implements OnInit {

  tweetFilterForm = this.formBuilder.group({
    timeFrom: null,
    timeTo: null,
    selectedTags: [],
    center: this.formBuilder.group({
      lat: null,
      lon: null
    }),
    radius: -1
  });

  apiLoaded: Observable<boolean>;

  constructor(private formBuilder: FormBuilder, private httpClient: HttpClient) {
    this.apiLoaded = httpClient.jsonp('https://maps.googleapis.com/maps/api/js?key=AIzaSyC_DnKvq3j_JZ3SQgNVkQOkE90Ii5p7kpU', 'callback')
      .pipe(
        map(() => true),
        catchError(() => of(false)),
      );
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    console.warn(this.tweetFilterForm.value);
  }
}
