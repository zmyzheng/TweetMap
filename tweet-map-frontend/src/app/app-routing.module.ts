import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MapComponent} from './map/map.component';
import {TopicListComponent} from './topic-list/topic-list.component';
import {TrendComponent} from './trend/trend.component';

const routes: Routes = [
  { path: '', redirectTo: '/map', pathMatch: 'full' },
  { path: 'map', component: MapComponent },
  { path: 'topics', component: TopicListComponent },
  { path: 'trend', component: TrendComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
