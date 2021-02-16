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
  center: google.maps.LatLngLiteral = {lat: 37.774546, lng: -122.433523};
  zoom = 1;
  currentPosition: google.maps.LatLngLiteral = {lat: 37.774546, lng: -122.433523};

  // heatmapOptions = {radius: 5};
  // heatmapData = [
  //   {lat: 37.782, lng: -122.447},
  //   {lat: 37.782, lng: -122.445},
  //   {lat: 37.782, lng: -122.443},
  //   {lat: 37.782, lng: -122.441},
  //   {lat: 37.782, lng: -122.439},
  //   {lat: 37.782, lng: -122.437},
  //   {lat: 37.782, lng: -122.435},
  //   {lat: 37.785, lng: -122.447},
  //   {lat: 37.785, lng: -122.445},
  //   {lat: 37.785, lng: -122.443},
  //   {lat: 37.785, lng: -122.441},
  //   {lat: 37.785, lng: -122.439},
  //   {lat: 37.785, lng: -122.437},
  //   {lat: 37.785, lng: -122.435}
  // ];


  constructor(private formBuilder: FormBuilder, private httpClient: HttpClient) {
    this.apiLoaded = httpClient.jsonp('https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=visualization', 'callback')
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

  onClickMap(event: google.maps.MapMouseEvent): void {
    this.center = event.latLng.toJSON();
  }

  onMapMouseMove(event: google.maps.MapMouseEvent): void {
    this.currentPosition = event.latLng.toJSON();
  }

}
