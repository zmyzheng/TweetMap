import { Component, OnInit } from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {filter} from 'rxjs/operators';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  tabs = [
    { tittle: 'Map', link: '/map' },
    { tittle: 'Topics', link: '/topics' },
    { tittle: 'Trend', link: '/trend' }
  ];
  path = '';

  // Step 1:
  // Create a property to track whether the menu is open.
  // Start with the menu collapsed so that it does not
  // appear initially when the page loads on a small screen!
  // https://ng-bootstrap.github.io/#/components/collapse/examples#navbar
  isMenuCollapsed = true;

  constructor(public router: Router) {
    // this is a workaround to get the current path. ActivedRoute is not working.
    // Discussion: https://github.com/angular/angular/issues/11023
    // https://github.com/angular/angular/issues/12623
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd )
    )
      .subscribe(event => this.path = (event as NavigationEnd).url);
  }


  ngOnInit(): void {
  }

}
