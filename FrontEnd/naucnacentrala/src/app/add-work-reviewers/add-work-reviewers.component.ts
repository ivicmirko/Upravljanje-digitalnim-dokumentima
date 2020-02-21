import { Component, OnInit } from '@angular/core';
import { FormGroupDirective, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { WorkService } from '../services/Work/work.service';
import { Router } from '@angular/router';
import { EditorWorkService } from '../services/EditorWork/editor-work.service';
import { EnumValue } from '../model/enum-value';
import { SearchService } from '../services/Search/search.service';

@Component({
  selector: 'app-add-work-reviewers',
  templateUrl: './add-work-reviewers.component.html',
  styleUrls: ['./add-work-reviewers.component.scss']
})
export class AddWorkReviewersComponent implements OnInit {

  private showList=true;
  private toSetDate=false;
  private works=[];
  private workId:Number;
  private title="";
  private setDateForm:FormGroup;
  private chooseReviewersForm:FormGroup;



  private reviewers=[];
  private submitted=false;
  private success=false;

  private submitted2=false;
  private success2=false;


  constructor(private workService:WorkService,private formBuilder:FormBuilder,private router:Router,
    private editorWorkService:EditorWorkService,private searchService:SearchService) { }

  ngOnInit() {
    this.editorWorkService.getWorksWithoutReviewers().subscribe(
      res=>{
        this.works=res;
      },
      error=>{
        console.log(error);
      }
    );

    this.setDateForm=this.formBuilder.group({
      reviewTime: ['',Validators.required],
    });

    this.chooseReviewersForm=this.formBuilder.group({
      chooseReviewers: ['',Validators.minLength(2)],
    });
  }

  downloadWorkPdf(id,processId,title){
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

  getReviewersBySA(workId){
    this.workId=workId;
    this.editorWorkService.getReviewersBySA(workId).subscribe(
      res=>{
        this.reviewers=res;
        this.showList=false;
        this.toSetDate=false;
        console.log(res);
      },
      error=>{
        console.log(error);
      }
    );
  }

  chooseReviewers(value,form){
    this.submitted=true;

    if(this.chooseReviewersForm.invalid){
      return;
    }

    this.success=true;

    let o ;
    o={
      reviewers:this.chooseReviewersForm.get('chooseReviewers').value
    }

    console.log(o);

    this.editorWorkService.postAddWorksReviewersForm(this.workId,o).subscribe(
      res=>{
        console.log(res);
        // this.toSetDate=true;
        // this.showList=false;
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/addWorkReviewers');
        ;
      },
      error=>{
        console.log(error);
      }
    );
    
  }

  bySA(){
    this.getReviewersBySA(this.workId);
  }

  byMoreLikeThis(){
    this.searchService.searchBtMoreLikeThis(this.workId).subscribe(
      res=>{
        console.log(res);
        this.reviewers=res;
      },
      error=>{
        console.log(error);
      }
    )
    
  }

  byGeoSpace(){
    this.searchService.searchByGeoSpacing(this.workId).subscribe(
      res=>{
        console.log(res);
        this.reviewers=res;
      },
      error=>{
        console.log(error);
      }
    );
  }

  // setDate(value,form){
  //   this.submitted2=true;

  //   if(this.setDateForm.invalid){
  //     return;
  //   }

  //   this.success2=true;

  //   let o = new Array();
  //   let temp="";
  //   for (var property in value) {
  //     console.log(property);
  //     console.log(value[property]);
  //       o.push({fieldId : property, fieldValue : value[property]});
  //   }
  
    
  //   this.editorWorkService.postSetReviewingTimeForm(this.processId,this.formFieldsDto2.taskId,o).subscribe(
  //     res=>{
  //       this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  //       this.router.onSameUrlNavigation = 'reload';
  //       this.router.navigateByUrl('/addWorkReviewers');
  //     },
  //     error=>{
  //       console.log(error);
  //     }
  //   );
  // }

}
