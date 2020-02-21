import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MagazineDTO } from 'src/app/model/magazine-dto';

const commonUrl = "http://localhost:8081/common/"
@Injectable({
  providedIn: 'root'
})
export class CommonService {

  private reviewersSAUrl=commonUrl+"getSAReviewers"
  private allSAUrl=commonUrl+"getScienceAreas";
  private freeEditorsUrl=commonUrl+"getFreeEditors";
  private getMyMagazineUrl=commonUrl+"getMyMagazine";
  private sendCorrectionUrl=commonUrl+"sendCorrection";
  constructor(private http:HttpClient) { }

  getSAReviewers(areas):Observable<any>{
    return this.http.post(this.reviewersSAUrl,areas);
  }

  getAllScienceAreas():Observable<any>{
    return this.http.get(this.allSAUrl);
  }

  getFreeEditors():Observable<any>{
    return this.http.get(this.freeEditorsUrl);
  }

  getMyMagazine():Observable<any>{
    return this.http.get(this.getMyMagazineUrl);
  }

  sendCorrection(magazine:MagazineDTO):Observable<any>{
    return this.http.post(this.sendCorrectionUrl,magazine);
  }
}
