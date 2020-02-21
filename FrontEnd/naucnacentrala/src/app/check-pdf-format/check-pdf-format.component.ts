import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { WorkService } from '../services/Work/work.service';
import { SessionStorageService } from '../services/SessionStorage/session-storage.service';
import { FormGroup } from '@angular/forms';
import { EditorWorkService } from '../services/EditorWork/editor-work.service';

@Component({
  selector: 'app-check-pdf-format',
  templateUrl: './check-pdf-format.component.html',
  styleUrls: ['./check-pdf-format.component.scss']
})
export class CheckPdfFormatComponent implements OnInit {

  private showList=true;
  private processInstance="";
  private taskId="";
  private formFieldsDto = null;
  private formFields = [];
  private checkPdfWorks=[];
  private processId:String;
  private workId:Number;


  constructor(private router:Router,private sanitizer: DomSanitizer,
    private editorWorkService:EditorWorkService, private sessionStorageService:SessionStorageService) {
    
      this.editorWorkService.getWorksForPdfCheck().subscribe(
      res=>{
        console.log(res);
        this.checkPdfWorks=res;
      },
      error=>{
        console.log(error);
      }
    );
   }

   

  ngOnInit() {
  
  }


  approveWork(workId){

    this.editorWorkService.approvePdf(workId).subscribe(
      res=>{
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/checkPDFWorks');
      },
      error=>{
        console.log(error);
      }
    )
  }

  fixNeed(workId){
    this.workId=workId;
    this.editorWorkService.getCommentForm(workId).subscribe(
      res=>{
        console.log(res);
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.showList=false;
      },
      error=>{
        console.log(error);
      }
    )

  }

  sendToFix(value, form){

    let o = new Array();
    console.log(value);
    console.log(form);
    for (var property in value) {
      if(property == 'editPdfDeadLine'){
        console.log(property);
        let temp=value[property];
        //temp=temp+"T00:00:00";
        o.push({fieldId : property, fieldValue : temp});
      }else{
        o.push({fieldId : property, fieldValue : value[property]});
      }
    }
    console.log(o);
    this.editorWorkService.sendPdfToFix(this.processInstance,this.formFieldsDto.taskId,o).subscribe(
      res=>{
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/checkPDFWorks');
      },
      error=>{
        console.log(error);
      }
    )
  }
}
