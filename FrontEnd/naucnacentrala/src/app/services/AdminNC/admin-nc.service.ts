import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const url = "http://localhost:8081/adminNC/"

@Injectable({
  providedIn: 'root'
})
export class AdminNCService {

  private getMagazineRequestsUrl=url+"getMagazineRequests";
  private approveMagazineUrl=url+"approveMagazine/";
  private magazineCorrectionUrl=url+"correctionMagazine/";

  constructor(private http:HttpClient) { }

  getMagazineRequests():Observable<any>{
    return this.http.get(this.getMagazineRequestsUrl);
  }

  approveMagazine(id:Number):Observable<any>{
    return this.http.get(this.approveMagazineUrl+id);
  }

  correctionMagazine(id:Number):Observable<any>{
    return this.http.get(this.magazineCorrectionUrl+id);
  }
}
