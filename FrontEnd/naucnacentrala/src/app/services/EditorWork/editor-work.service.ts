import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const url='http://localhost:8081/editorWork/';
@Injectable({
  providedIn: 'root'
})
export class EditorWorkService {

  private getReviewersBySAUrl=url+"getReviewersBySA/" //workId
  
  private getAllArrivedWorksUrl=url+"getArrivedWorks";
  private getCheckWorkFormUrl=url+"getCheckWorkForm/";
  private approveWorkUrl=url+"detailsCorrect/";
  private denyWorkUrl=url+"detailsDenied/";
  
  private getAllWorksForPdfCheckUrl=url+"worksForPdfCheck";
  private approvePdfUrl=url+"approvePdf/";
  private needToFixPdfUrl=url+"needToFixPdf/";
  private sendCommentForPdfUrl=url+"sendCommentForPdf/";

  private getWorksWithoutReviewersUrl=url+"worksWithoutReviewers"
  private getAddWorksReviewersFormUrl=url+"getWorksReviewersForm/";
  private addWorksReviewersFormUrl=url+"addWorksReviewersForm/";
  private getSetReviewingTimeFormUrl=url+"getSetReviewingTimeForm/";
  private postSetReviewingTimeFormUrl=url+"setReviewingTimeForm/";

  private getReviewedWorksUrl=url+"getReviewedWorks";
  private openReviewedWorkUrl=url+"openReviewedWork/";//processId,workId;
  private makeDecisionUrl=url+"makeDecision/"; //processId/decision

  private postSetNewReviewerFormUrl=url+"postSetNewReviewerForm/";//processId,oldUsername;
  private getSetNewReviewerFormUrl=url+"getSetNewReviewerForm/";//processId
  
  constructor(private http:HttpClient) { }

  getReviewersBySA(workId):Observable<any>{
    return this.http.get(this.getReviewersBySAUrl+workId);
  }

  getSetNewReviewerForm(processId,oldUsername):Observable<any>{
    return this.http.get(this.getSetNewReviewerFormUrl+processId+"/"+oldUsername);
  }

  postSetNewReviewerForm(oldUsername,processId,o):Observable<any>{
    return this.http.post(this.postSetNewReviewerFormUrl+processId+"/"+oldUsername,o);
  }

  getReviewedWorks():Observable<any>{
    return this.http.get(this.getReviewedWorksUrl);
  }
  
  openReviewedWork(workId):Observable<any>{
    return this.http.get(this.openReviewedWorkUrl+workId);
  }

  makeDecision(processId,decision):Observable<any>{
    return this.http.get(this.makeDecisionUrl+processId+"/"+decision);
  }

  getArrivedWorks():Observable<any>{
    return this.http.get(this.getAllArrivedWorksUrl);
  }

  getCheckWorkForm(workId):Observable<any>{
    return this.http.get(this.getCheckWorkFormUrl+workId);
  }

  approveWorkDetails(workId):Observable<Blob>{
    let url=this.approveWorkUrl+workId;
    // const httpOptions = {
    //   headers: new HttpHeaders({ 'Content-Type': 'application/pdf', "responseType": 'application/pdf' })
    // };
    // return this.http.get<String>(url,httpOptions);
    return this.http.get(url, {responseType: 'blob'});
    //return this.http.get(this.approveWorkUrl+processId+"/"+workId,headers);
  }

  denyWorkDetails(workId):Observable<any>{
    return this.http.get(this.denyWorkUrl+workId);
  }

  getWorksForPdfCheck():Observable<any>{
    return this.http.get(this.getAllWorksForPdfCheckUrl);
  }

  approvePdf(workId):Observable<any>{
    return this.http.get(this.approvePdfUrl+workId);
  }

  getCommentForm(processId):Observable<any>{
    return this.http.get(this.needToFixPdfUrl+processId);
  }

  sendPdfToFix(processId,taskId,o):Observable<any>{
    return this.http.post(this.sendCommentForPdfUrl+processId+"/"+taskId,o);
  }

  getWorksWithoutReviewers():Observable<any>{
    return this.http.get(this.getWorksWithoutReviewersUrl);
  }

  getAddReviewersForm(workId):Observable<any>{
    return this.http.get(this.getAddWorksReviewersFormUrl+workId);
  }

  postAddWorksReviewersForm(workId,o):Observable<any>{
    return this.http.post(this.addWorksReviewersFormUrl+workId,o);
  }

  getSetReviewingTimeForm(processId):Observable<any>{
    return this.http.get(this.getSetReviewingTimeFormUrl+processId);
  }

  postSetReviewingTimeForm(processId,taskId,o):Observable<any>{
    return this.http.post(this.postSetReviewingTimeFormUrl+processId+"/"+taskId,o);
  }
}
