import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { LoginParams } from 'src/app/model/login-params';
import { ProfileDTO } from 'src/app/model/profile-dto';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json',            
  })
};

const authUrl = "http://localhost:8081/auth/"


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl= authUrl + 'login';
  private getLoggedUrl= authUrl + 'getLogged';
  private logoutUrl= authUrl + 'user/logout';
  private testUrl=authUrl+'test1';

  constructor(private http:HttpClient) { 
  }

  signIn(loginParams : LoginParams): Observable<ProfileDTO>{
    return this.http.post<ProfileDTO>(this.loginUrl,loginParams);
  }

  getLogged():Observable<ProfileDTO>{
    return this.http.get<ProfileDTO>(this.getLoggedUrl);
  }

  logOut():Observable<any>{
    return this.http.get<any>(this.logoutUrl);
  }

  test():Observable<any>{
    return this.http.get<any>("http://localhost:8081/test/test1");
  }
}
