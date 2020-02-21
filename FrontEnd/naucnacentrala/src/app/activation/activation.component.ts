import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from '../services/Registration/registration.service';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.scss']
})
export class ActivationComponent implements OnInit {

  private username;
  private processId;

  constructor(private route: ActivatedRoute, private registrationService: RegistrationService,private router:Router) {
    this.route.params.subscribe( params => {
      this.username = params.username; 
      this.processId = params.processId;
    });
   }

  ngOnInit() {
  }

  activate(){
    console.log('activate');
    this.registrationService.activateUser(this.username,this.processId).subscribe(
      res => {
        console.log("res", res);
        this.router.navigate(['/']);
    
      },
      err => {
        console.log("Error occured");
      }
    );
  }
}
