import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ProfileDTO } from 'src/app/model/profile-dto';

@Injectable({
  providedIn: 'root'
})
export class ShareService {

  shareIsLogged= new Subject<any>();
  shareProfile= new Subject<ProfileDTO>();

  constructor() { }

  sendIsLogged(isLogged:boolean){
    this.shareIsLogged.next(isLogged);
  }

  sendProfile(profile:ProfileDTO){
    this.shareProfile.next(profile);
  }
}
