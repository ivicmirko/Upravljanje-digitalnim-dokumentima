import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
const url='http://localhost:8081/reviewer/';

@Injectable({
  providedIn: 'root'
})
export class ReviewerService {

  private getWorksForReviewingUrl=url+"getWorksForReviewing";
  private getReviewingFormUrl=url+"getReviewingForm/" //processId
  private downloadPdfUrl=url+"downloadPdf/" //processId+workId
  private postReviewingFormUrl=url+"postReviewingForm/" //processId taskId
  
  constructor(private http:HttpClient) { }

  postReviewingForm(workId,o):Observable<any>{
    return this.http.post(this.postReviewingFormUrl+workId,o);
  }

  getWorksForReviewing():Observable<any>{
    return this.http.get(this.getWorksForReviewingUrl);
  }

  getReviewingForm(processId):Observable<any>{
    return this.http.get(this.getReviewingFormUrl+processId);
  }

  downloadPdf(workId):Observable<any>{
    return this.http.get(this.downloadPdfUrl+workId,{responseType: 'blob'});
  }
}
