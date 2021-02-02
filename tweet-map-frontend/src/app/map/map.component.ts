import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  tweetFilterForm = this.formBuilder.group({
    timeFrom: null,
    timeTo: null,
    selectedTags: [],
    center: { lat: 0, lon: 0 },
    radius: 0
  });

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

}
