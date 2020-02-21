import { Injectable } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  private previousUrl: string;
  private currentUrl: string;

  constructor(private router: Router) { 
    this.router.events.subscribe(event=>
      {
        if(event instanceof NavigationEnd){
          this.previousUrl=this.currentUrl;
          this.currentUrl=event.url;
        }
      });
  }

  public getPreviousUrl(){
    return this.previousUrl;
  }
}
