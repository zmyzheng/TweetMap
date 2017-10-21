import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router';
import {HttpModule} from "@angular/http";

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';

import { AgmCoreModule } from '@agm/core';
import { TweetService } from './services/tweet.service';
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot([
      { path: '', redirectTo: '/home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent }
    ]),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBSExeeibbSosKkwhRk1P07EADD_ZbH3A4'
    })
  ],
  providers: [TweetService],
  bootstrap: [AppComponent]
})
export class AppModule { }
