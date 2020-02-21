import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const url='http://localhost:8081/work/';
const urlCommon='http://localhost:8081/common/'

@Injectable({
  providedIn: 'root'
})
export class WorkService {

  private getAllMagazinesUrl=urlCommon+"getAllMagazines";
  private setWorkMagazineUrl=url+"setWorkMagazine/" //magazine id
  private setWorkDetailsUrl=url+"setWorkDetails/" //magazineId
  private postPdfFileUrl=url+"postPdfFile/"; //workId
  private addCoAuthorUrl=url+"addCoauthor/"; //workId

  private getChoosingMagazineFormUrl=url+"getChoosingMagazineForm";
  private postChoosingMagazineFormUrl=url+"postChoosingMagazineForm/";
  private getFormForWorkDetailsUrl=url+"getFormForWorkDetails/";
  private postFormForWorkDetailsUrl=url+"postFormForWorkingDetails/";
  private getCoAuthorsFormUrl=url+"getCoAuthorsForm/";
  private postCoauthorFormUrl=url+"postCoauthorForm/";
  
  private getWorksForPdfFixUrl=url+"getWorksForPdfFormatFix";
  private getWorkForPdfFormatFixUrl=url+"getWorkForPdfFormatFix/"
  private sendNewPdfUrl=url+"sendFixedPdfFormatFile/"

  private downloadProposalPdfFileUrl=url+"downloadProposalPdfFile/"


  constructor(private http:HttpClient) { }


  getAllMagazines():Observable<any>{
    return this.http.get(this.getAllMagazinesUrl);
  }

  setWorkMagazine(magazineId):Observable<any>{
    return this.http.get(this.setWorkMagazineUrl+magazineId);
  }

  setWorkDetails(magaizneId,workDetails):Observable<any>{
    return this.http.post(this.setWorkDetailsUrl+magaizneId,workDetails);
  }

  sendFile(pdfFile,workId){
    const formData:FormData=new FormData();
    //let ret=new Array();
    //ret.push({pdfWorkForma:pdfFile});
    //ret.push({dataForm:o});
    formData.append("pdfWork",pdfFile);
    return this.http.post(this.postPdfFileUrl+workId,formData);

    console.log(formData);
  }

  addCoAuthor(workId,coAuthor):Observable<any>{
    return this.http.post(this.addCoAuthorUrl+workId,coAuthor);
  }

  getWorksForPdfFix():Observable<any>{
    return this.http.get(this.getWorksForPdfFixUrl);
  }

  getWorkForPdfFix(processId):Observable<any>{
    return this.http.get(this.getWorkForPdfFormatFixUrl+processId);
  }

  sendNewPdfFormatFix(processId,taskId,pdfWork):Observable<any>{
    const formData:FormData=new FormData();
    formData.append("pdfWork",pdfWork);
    return this.http.post(this.sendNewPdfUrl+processId+"/"+taskId,formData);
  }

  downloadProposalPdfFile(workId):Observable<any>{
    return this.http.get(this.downloadProposalPdfFileUrl+workId);
  }


}
