import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AgmCoreModule } from 'angular2-google-maps/core';

import { IndexComponent } from './index/component/index.component';

@NgModule({
    declarations: [
        IndexComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AgmCoreModule.forRoot({
            apiKey: 'AIzaSyCvSxaeNAxaziOKTelih7xX3OxeCa6_2Zk'
        })
    ],
    providers: [],
    bootstrap: [IndexComponent]
})
export class AppModule {
}
