import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest,HttpResponse, HttpHandler, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SessionStorageService } from '../SessionStorage/session-storage.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{
  
  constructor(private sessionStorageService:SessionStorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): import("rxjs").Observable<import("@angular/common/http").HttpEvent<any>> {
    
    let tokenizeReq=req;
    const token=this.sessionStorageService.getToken();

    let parametar='Bearer '+token;
    if(token != null){
      
      tokenizeReq=req.clone({
        setHeaders:{
          Authorization: parametar,
          
        }
        // headers: req.headers.set('token', `Bearer ${token}`)
      });
    }else{
      console.log('NO TOKEN');
    }
    console.log(tokenizeReq);

    return next.handle(tokenizeReq);
  }

  // const req1 = req.clone({
  //   headers: req.headers.set('Authorization', `Bearer ${token}`),
  // });
}

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true }
];
