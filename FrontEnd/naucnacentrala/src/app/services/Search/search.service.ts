import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
const url='http://localhost:8081/search/';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private searcchByTitleUrl=url+"byTitle/" //title/phrase
  private searcchByMagazineNameUrl=url+"byMagazineName/" //title/phrase
  private searcchByKeyTermsUrl=url+"byKeyTerms/" //title/phrase
  private searcchByAuthorsUrl=url+"byAuthors/" //title/phrase
  private searcchByScienceAreasUrl=url+"byScienceAreas/" //scienceAreas
  private searchByWorkContentUrl=url+"byWorkContent/" //scienceAreas

  private getByGeoSpacingUrl=url+"getReviewersByLocation/"//workId
  private getByMoreLikeThisUrl=url+"getMoreLikeThisReviewers/"//workId

  constructor(private http:HttpClient) { }

  searchByTitle(title,phrase):Observable<any>{
    return this.http.get(this.searcchByTitleUrl+phrase+"/"+title);
  }

  searchByMagazineName(name,phrase):Observable<any>{
    return this.http.get(this.searcchByMagazineNameUrl+phrase+"/"+name);
  }

  searchByKeyTerms(keyTerms,phrase):Observable<any>{
    return this.http.get(this.searcchByKeyTermsUrl+phrase+"/"+keyTerms);
  }

  searchByAuthors(authors,phrase):Observable<any>{
    return this.http.get(this.searcchByAuthorsUrl+phrase+"/"+authors);
  }

  searchByScienceAreas(sa):Observable<any>{
    return this.http.get(this.searcchByScienceAreasUrl+sa);
  }

  searchByWorkContent(workContent,phrase):Observable<any>{
    return this.http.get(this.searchByWorkContentUrl+phrase+"/"+workContent);
  }

  searchByGeoSpacing(wokrId):Observable<any>{
    return this.http.get(this.getByGeoSpacingUrl+wokrId);
  }

  searchBtMoreLikeThis(workId):Observable<any>{
    return this.http.get(this.getByMoreLikeThisUrl+workId);
  }

  advancedSearch(o):Observable<any>{
    return this.http.post("http://localhost:8081/advancedSearch/search",o);
  }
}
