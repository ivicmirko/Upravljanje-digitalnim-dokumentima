import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MagazineDTO } from 'src/app/model/magazine-dto';
const url='http://localhost:8081/magazine/';
@Injectable({
  providedIn: 'root'
})
export class NewMagazineService {

  private sendMagazineDataUrl=url+"newMagazine"
  private sendEditorialBoardUrl=url+"addEditorialBoard";
  constructor(private httpClient:HttpClient) { }

  startProcess():Observable<any>{
    return this.httpClient.get(url+'getDataInputForm');
  }

  sendData(user, taskId):Observable<any> {
    return this.httpClient.post(url+"postDataInputForm/".concat(taskId), user);
  }

  sendMagazineData(magazine:MagazineDTO):Observable<any>{
    return this.httpClient.post(this.sendMagazineDataUrl,magazine);
  }

  sendEditorialBoard(magazine:MagazineDTO):Observable<any>{
    return this.httpClient.post(this.sendEditorialBoardUrl,magazine);
  }

}
