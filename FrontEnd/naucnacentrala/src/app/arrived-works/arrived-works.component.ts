import { Component, OnInit } from '@angular/core';
import { WorkService } from '../services/Work/work.service';
import { SessionStorageService } from '../services/SessionStorage/session-storage.service';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { EditorWorkService } from '../services/EditorWork/editor-work.service';

@Component({
  selector: 'app-arrived-works',
  templateUrl: './arrived-works.component.html',
  styleUrls: ['./arrived-works.component.scss']
})
export class ArrivedWorksComponent implements OnInit {

  private showList=true;
  private arrivedWorks=[];
  private workId:Number;
  private work;
  private title="";
  private processId;
  fileUrl

  constructor(private router:Router,private sanitizer: DomSanitizer,
    private editorWorkService:EditorWorkService, private sessionStorageService:SessionStorageService) {
    
      this.editorWorkService.getArrivedWorks().subscribe(
      res=>{
        console.log(res);
        this.arrivedWorks=res;
      },
      error=>{
        console.log(error);
      }
    );
   }

   

  ngOnInit() {
  
  }

  getCheckWorkForm(workId,title){
    this.workId=workId;
    this.title=title;
    this.editorWorkService.getCheckWorkForm(workId).subscribe(
      res=>{
        this.workId=res.id;
        console.log(res);
        this.work=res;
        this.showList=false;
      },
      error=>{
        console.log(error);
      }
    );
  }

  approveWorkDetails(){
    this.editorWorkService.approveWorkDetails(this.workId).subscribe(
      res=>{
        console.log("Preuzima PDF");
        console.log(res);
        
        let blob = new Blob([res], { type: 'blob' });
        let url= window.URL.createObjectURL(res);
        
        var link = document.createElement('a');
            link.href = url;
            link.download = this.title+".pdf";
            // this is necessary as link.click() does not work on the latest firefox
            link.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));

 
        //window.open(url);
        //saveAs(blob,this.title+".pdf");
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/arrivedWorks');
        
      },
      error=>{
        console.log(error);
      }
    );
  }

  denyWorkDetails(){
    this.editorWorkService.denyWorkDetails(this.workId).subscribe(
      res=>{
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/arrivedWorks');
      },
      error=>{
        console.log(error);
      }
    )
  }



}
