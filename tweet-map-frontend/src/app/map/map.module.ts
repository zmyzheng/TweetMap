import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MapRoutingModule } from './map-routing.module';
import { VisualizationComponent } from './visualization/visualization.component';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {GoogleMapsModule} from '@angular/google-maps';
import {HttpClientJsonpModule, HttpClientModule} from '@angular/common/http';




@NgModule({
  declarations: [VisualizationComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MapRoutingModule,
    NgbModule,
    GoogleMapsModule,
    HttpClientModule,
    HttpClientJsonpModule,
  ]
})
export class MapModule { }
