import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminNCService } from '../services/AdminNC/admin-nc.service';

@Component({
  selector: 'app-magazine-overview',
  templateUrl: './magazine-overview.component.html',
  styleUrls: ['./magazine-overview.component.scss']
})
export class MagazineOverviewComponent implements OnInit {

  private magazines=[];
  private toCorrection=false;
  
  constructor(private router:Router,private adminNCService:AdminNCService) { }

  ngOnInit() {

    this.adminNCService.getMagazineRequests().subscribe(
      res=>{
        this.magazines=res;
        console.log(res);
      },
      error=>{
        console.log(error);
      }
    );
  }

  approveMagazine(id:Number){
    
    this.adminNCService.approveMagazine(id).subscribe(
      res=>{
        alert('Casopis odobren!')
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/newMagazinesRequests');  
    },
    error=>{
      console.log(error);
    });
  }

  correctionMagazine(id:Number){

    this.adminNCService.correctionMagazine(id).subscribe(
      res=>{
        alert('Casopis poslat na doradu');
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/newMagazinesRequests'); 
      },
      error=>{
        console.log(error);
      }
    )
  }

}
