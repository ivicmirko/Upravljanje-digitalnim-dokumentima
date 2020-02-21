import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
const url='http://localhost:8081/author/';

@Injectable({
  providedIn: 'root'
})
export class AuthorWorkService {

  private getMyApprovedWorksUrl=url+"getMyApprovedWorks";
  private getMyWorksForFixUrl=url+"getMyWorksForFix";
  private openWorkForFixUrl=url+"openWorkForFix/";//processId workId
  private getWorkFixFormUrl=url+"getWorkFixForm/"; //processId
  private postWorkFixFormUrl=url+"postWorkFixForm/";//processId
  private postPdfFileUrl=url+"postPdfFile/";//processId

  constructor(private http:HttpClient) { }

  getMyApprovedWorks():Observable<any>{
    return this.http.get(this.getMyApprovedWorksUrl);
  }

  getMyWorksForFix():Observable<any>{
    return this.http.get(this.getMyWorksForFixUrl);
  }

  openWorkForFix(processId,workId):Observable<any>{
    return this.http.get(this.openWorkForFixUrl+processId+"/"+workId);
  }

  getWorkFixForm(processId):Observable<any>{
    return this.http.get(this.getWorkFixFormUrl+processId);
  }

  postWorkFixForm(processId,o):Observable<any>{
    return this.http.post(this.postWorkFixFormUrl+processId,o);
  }

  postPdfFile(processId,pdfWork):Observable<any>{
    const formData:FormData=new FormData();
    formData.append("pdfWork",pdfWork);
    return this.http.post(this.postPdfFileUrl+processId,formData);
  }
}
