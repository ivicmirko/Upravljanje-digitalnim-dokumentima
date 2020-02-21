import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionStorageService } from '../services/SessionStorage/session-storage.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { AuthService } from '../services/Auth/auth.service';
import { LoginParams } from '../model/login-params';
import { ProfileDTO } from '../model/profile-dto';
import { ShareService } from '../services/Share/share.service';
import { RouterService } from '../services/Router/router.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm : FormGroup;
  submitted = false;
  success = false;
  isLogged=false;
  errorMessage='';
  private loginParams: LoginParams;
  private profile: ProfileDTO;

  constructor(private formBuilder: FormBuilder, private sessionStorageService:SessionStorageService, private router: Router,
    private location:Location, private authService:AuthService, private shareService:ShareService, private routerService:RouterService,
    ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required,Validators.minLength(3)])],
      password: ['', Validators.required]
    });
  }



  login(){
    this.submitted=true;

    if(this.loginForm.invalid){
      return;
    }

    this.success=true;


    this.loginParams= new LoginParams(
      this.loginForm.get('username').value,
      this.loginForm.get('password').value
    );

    console.log(this.loginParams);
    this.authService.signIn(this.loginParams).subscribe(
      data=>
      {
        this.profile=data;
        console.log('huraa');
        console.log(data);
        this.sessionStorageService.saveToken(data.token);
        this.sessionStorageService.saveProfile(data);

        this.isLogged=true;
        this.shareService.sendProfile(this.profile);
        this.shareService.sendIsLogged(true);
        console.log(this.profile);
        
        if(this.routerService.getPreviousUrl() != null){
          if(this.routerService.getPreviousUrl().includes('register')){
            this.router.navigate(['']);
          }else if(this.routerService.getPreviousUrl().includes('admin')){
            this.router.navigate(['']);
          }else{
            this.location.back();
          }
        
        }else{
          this.router.navigate(['']);
        }
        
      },
      error=>{
        this.errorMessage="Wrong password, please try again.";
        alert("Pogresan password ili korisnicko ime!");
        this.success=false;
      })

  }

}
