import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';

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

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    console.warn(this.tweetFilterForm.value);
  }
}
