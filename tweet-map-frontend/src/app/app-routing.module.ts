import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TopicListComponent} from './topic-list/topic-list.component';
import {TrendComponent} from './trend/trend.component';

const routes: Routes = [
  { path: '', redirectTo: 'map', pathMatch: 'full' },

  { path: 'topics', component: TopicListComponent },
  { path: 'trend', component: TrendComponent },
  { path: 'map', loadChildren: () => import('./map/map.module').then(m => m.MapModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
