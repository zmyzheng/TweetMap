import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {VisualizationComponent} from './visualization/visualization.component';



const routes: Routes = [
  { path: '', redirectTo: 'visualization', pathMatch: 'full' },
  { path: 'visualization', component: VisualizationComponent }
  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MapRoutingModule { }
