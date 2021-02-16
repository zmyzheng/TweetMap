import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MapRoutingModule } from './map-routing.module';
import { VisualizationComponent } from './visualization/visualization.component';

import {ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';




@NgModule({
  declarations: [VisualizationComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MapRoutingModule,
    NgbModule,

  ]
})
export class MapModule { }
