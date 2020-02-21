import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EditorWorkService } from '../services/EditorWork/editor-work.service';
import { error } from '@angular/compiler/src/util';

@Component({
  selector: 'app-making-decision',
  templateUrl: './making-decision.component.html',
  styleUrls: ['./making-decision.component.scss']
})
export class MakingDecisionComponent implements OnInit {


  private showList=true;
  private processInstance="";
  private works=[];
  private workReviews=[];
  private answer="";
  private processId:String;
  private workId:Number;
  private title="";
  private showAnswer=false;


  constructor(private router:Router,private editorService:EditorWorkService) { }

  ngOnInit() {

    this.editorService.getReviewedWorks().subscribe(
      res=>{
        console.log(res);
        this.works=res;
      },
      error=>{
        console.log(error);
      }
    );
  }

  openWork(id,title){
    this.workId=id;
    this.title=title;

    this.editorService.openReviewedWork(id).subscribe(
      res=>{
        console.log(res);
        this.workReviews=res.reviewedWorkDTOS;
        this.answer=res.answer;
        if(this.answer == null || this.answer==""){
          this.showAnswer=false;
        }else{
          this.showAnswer=true;
        }
        this.showList=false;
      },
      error=>{
        console.log(error);
      }
    );
  }

  approveWork(){
    this.editorService.makeDecision(this.workId,1).subscribe(
      res=>{
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/reviewedWorks');
      },
      error=>{
        console.log(error);
      }
    );
  }

  denyWork(){
    this.editorService.makeDecision(this.processId,2).subscribe(
      res=>{
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/reviewedWorks');
      },
      error=>{
        console.log(error);
      }
    );
  }

  smallChanges(){
    this.editorService.makeDecision(this.processId,3).subscribe(
      res=>{
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/reviewedWorks');
      },
      error=>{
        console.log(error);
      }
    );
  }

  bigChanges(){
    this.editorService.makeDecision(this.processId,4).subscribe(
      res=>{
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigateByUrl('/reviewedWorks');
      },
      error=>{
        console.log(error);
      }
    );
  }

}
