import { Component, OnInit } from '@angular/core';
import { ProfileDTO } from '../model/profile-dto';
import { Router } from '@angular/router';
import { SessionStorageService } from '../services/SessionStorage/session-storage.service';
import { AuthService } from '../services/Auth/auth.service';
import { ShareService } from '../services/Share/share.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  profile: ProfileDTO;
  username:string;
  anyLogged=false;
  adminLogged=false;
  role:String;
  
  constructor(private sessionStorageService:SessionStorageService,private authService:AuthService,private shareService:ShareService,
    private router:Router) { 
      console.log("Konstruktor od nav");
      this.getLoggedUser();
    }

  ngOnInit() {
    console.log("NgInit od nav");
    this.shareService.shareIsLogged.subscribe(receiveddata=>{
      this.anyLogged=receiveddata;
    });

    this.shareService.shareProfile.subscribe(receiveddata=>{
      this.profile=receiveddata;
      this.role=this.profile.authorities[0];
      // if(role == 'admin'){
      //   this.adminLogged=true;
      // }else{
      //   this.adminLogged=false;
      // }
      //ZGODNA BI BILA PROVERA DA LI VEC IMA CASOPIS I AKO IMA UKINUTI LINK KA "DODAJ NOVI CASOPIS"
    });

    this.getLoggedUser();
  }

  logOut(){
    this.authService.logOut().subscribe(data=>
      {
  
      },
      error=>{
        alert('greska');
      })
    this.sessionStorageService.signOut();
    this.anyLogged=false;
    this.adminLogged=false;
    this.router.navigate(['login']);
  }

  getLoggedUser(){
    console.log('loog');
    this.authService.getLogged().subscribe(data=>{
      console.log('usa');
      this.profile=data;
      this.anyLogged=true;
      this.shareService.sendIsLogged(this.anyLogged);
      this.shareService.sendProfile(this.profile);
      let role=this.profile.authorities[0];
      if(role == 'admin'){
        this.adminLogged=true;
      }else{
        this.adminLogged=false;
      }
    },
    error=>{
      this.profile=null;
      this.anyLogged=false;
      this.adminLogged=false;
    });
  }

  test(){
    console.log('Usao u test service');
    this.authService.test().subscribe(res=>{
      console.log(res);
    },
    error=>{
      console.log(error);
    });
  }

}
