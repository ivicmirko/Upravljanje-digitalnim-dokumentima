import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../services/Registration/registration.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reviewer-conformation',
  templateUrl: './reviewer-conformation.component.html',
  styleUrls: ['./reviewer-conformation.component.scss']
})
export class ReviewerConformationComponent implements OnInit {

  private requests = [];
  
  constructor(private registrationService: RegistrationService,private router:Router) { }

  ngOnInit() {
    this.registrationService.getAdminNCTasks().subscribe(
      res => {
        this.requests = res;
        console.log(this.requests);
      },
      err => {
        console.log("Error occured");
      }
    );
     
  }

  approve(taskId){
    console.log("prihvati: ", taskId);

    this.registrationService.approveReviewer(taskId).subscribe(
      res => {
        alert("Korisnik uspesno potvrdjen kao recenzent");
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/reviewerRequests');
    
      },
      err => {
        console.log("Error occured");
      }
    );
  }

  deny(taskId){
    console.log("odbij: ", taskId);

    this.registrationService.denyReviewer(taskId).subscribe(
      res => {
        alert("Korisnik odbijen kao recenzent");
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/reviewerRequests');    
      },
      err => {
        console.log("Error occured");
      }
    );

  }
}
