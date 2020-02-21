import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const url='http://localhost:8081/auth/';
const urlAdminNC='http://localhost:8081/adminNC/';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpClient:HttpClient) { }

  startProcess():Observable<any>{
    return this.httpClient.get(url+'getDataInputForm');
  }

  registerUser(user, taskId,processId):Observable<any> {
    return this.httpClient.post(url+"postDataInputForm/"+taskId+"/"+processId, user);
  }

  activateUser(username,processId):Observable<any>{
    return this.httpClient.get(url+"activating/"+username+"/"+processId);
  }

  getAdminNCTasks():Observable<any>{
    return this.httpClient.get(urlAdminNC + "getReviewerRequests");
  }

  approveReviewer(taskId):Observable<any>{
    return this.httpClient.get(urlAdminNC+"approveReviewer/"+taskId);
  }

  denyReviewer(taskId):Observable<any>{
    return this.httpClient.get(urlAdminNC+"denyReviewer/"+taskId);
  }
}
