import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WorkService } from '../services/Work/work.service';
import { DomSanitizer } from '@angular/platform-browser';
import { SessionStorageService } from '../services/SessionStorage/session-storage.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-fixing-pdf-format',
  templateUrl: './fixing-pdf-format.component.html',
  styleUrls: ['./fixing-pdf-format.component.scss']
})
export class FixingPdfFormatComponent implements OnInit {

  private showList=true;
  private processInstance="";
  private taskId="";
  private formFieldsDto = null;
  private formFields = [];
  private checkPdfWorks=[];
  private processId:String;
  private workId:Number;
  private fixingPdfFormatForm:FormGroup;
  private pdfFile:File;
  private submitted=false;
  private success=false;

  constructor(private formBuilder:FormBuilder,private router:Router,private sanitizer: DomSanitizer,
    private workService:WorkService, private sessionStorageService:SessionStorageService) {
    
      this.workService.getWorksForPdfFix().subscribe(
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
  
    this.fixingPdfFormatForm=this.formBuilder.group({
      commentAuthor:[],
      newPdfAuthor:['',Validators.required]
    });
  }

  pdfSelected(event){
    this.pdfFile=<File>event.target.files[0];
    console.log(event);
  }

  openFixWorkFormat(id,processId){
    this.processInstance=processId;
    
    this.workService.getWorkForPdfFix(processId).subscribe(
      res=>{
        console.log(res);
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.taskId=res.taskId;
        this.showList=false;
      },
      error=>{
        console.log(error);
      }
    )
  };

  sendFixedPdfFormat(value,form){
    this.submitted=true;

    if(this.fixingPdfFormatForm.invalid){
      return;
    }

    this.success=true;

    this.workService.sendNewPdfFormatFix(this.processInstance,this.taskId,this.pdfFile).subscribe(
      res=>{
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/fixingPdfFormat');
      },
      error=>{
        console.log(error);
      }
    );
  }
}
