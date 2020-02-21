import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthorWorkService } from '../services/AuthorWork/author-work.service';

@Component({
  selector: 'app-fix-work',
  templateUrl: './fix-work.component.html',
  styleUrls: ['./fix-work.component.scss']
})
export class FixWorkComponent implements OnInit {

  private showList=true;
  private toSendNewWork=false;
  private processInstance="";
  private formFieldsDto = null;
  private formFields = [];
  private works=[];
  private workReviews=[];
  private processId:String;
  private workId:Number;
  private title="";
  private fixingWorkForm:FormGroup;

  private submitted=false;
  private success=false;
  pdfFile:File;
  
  constructor(private router:Router,private formBuilder:FormBuilder,private authorService:AuthorWorkService) { }

  ngOnInit() {
    this.fixingWorkForm=this.formBuilder.group({
      answerForReviewer:['',Validators.required],
      newPdfFile:['',Validators.required],
    });

    this.authorService.getMyWorksForFix().subscribe(
      res=>{
        console.log(res);
        this.works=res;
      },
      error=>{
        console.log(error);
      }
    );
  }

  openWork(workId,processId,title){
    this.processId=processId;
    this.workId=workId;
    

    this.authorService.openWorkForFix(processId,workId).subscribe(
      res=>{
        console.log(res);
        this.workReviews=res.reviewedWorkDTOS;
        this.showList=false;
        this.toSendNewWork=false;
      },
      error=>{
        console.log(error);
      }
    );
  }

  getfixWorkForm(){
    
    
    this.authorService.getWorkFixForm(this.processId).subscribe(
      res=>{
        console.log(res);
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.showList=false;
        this.toSendNewWork=true;
      },
      error=>{
        console.log(error);
      }
    );
  }

  pdfSelected(event){
    this.pdfFile=<File>event.target.files[0];
    console.log(event);
  }

  postWorkFixForm(value,form){

    this.submitted=true;

    if(this.fixingWorkForm.invalid){
      return;
    }

    this.success=true;

    let o = new Array();

    for (var property in value) {
      if(property != 'newPdfFile'){
        o.push({fieldId : property, fieldValue : value[property]});
      }
    }

    this.authorService.postWorkFixForm(this.processId,o).subscribe(
      res=>{
        if(res.message='ok'){
          this.authorService.postPdfFile(this.processId,this.pdfFile).subscribe(
            res=>{
              this.router.routeReuseStrategy.shouldReuseRoute = () => false;
              this.router.onSameUrlNavigation = 'reload';
              this.router.navigateByUrl('/worksForFix');
            },
            error=>{
              console.log(error);
            }
          );
        }
      },
      error=>{
        console.log(error);
      }
    );
  }

}
