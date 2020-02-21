import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SearchService } from '../services/Search/search.service';
import { CommonService } from '../services/Common/common.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  private searchForm:FormGroup;
  private searchType=0;
  private showList=false;
  private works=[];
  private scienceAreas=[];

  private isChecked1=false;
  private isChecked2=false;
  private isChecked3=false;
  private isChecked4=false;
  private isChecked5=false;
  private isChecked6=false;
  private title="";
  private magazineName="";
  private keyTerms="";
  private authors="";
  private scienceAreass="";
  private content="";

  constructor(private searchService:SearchService,private router:Router,private route:ActivatedRoute,private formBuilder:FormBuilder,
    private commonService:CommonService) { 
    this.route.params.subscribe( params => {
      this.searchType = params.searchType; 
    });
    this.showList=false;
  }

  ngOnInit() {
    this.searchForm=this.formBuilder.group({
      title:[''],
      magazineName:[''],
      keyTerms:[],
      authors:[''],
      scienceArea:['']
    });

    this.showList=false;
    this.commonService.getAllScienceAreas().subscribe(
      res=>{
        console.log(res);
        this.scienceAreas=res;
      },
      error=>{
        console.log(error);
      }
    );


  }

  back(){
    this.showList=false;
  }

  search(){
    if(this.searchType==1){
            //------------------------------------------------------------------

      let phrase=0;
      
      if(this.isChecked1){
       phrase=1; 
      }

      this.searchService.searchByMagazineName(this.magazineName,phrase).subscribe(
        res=>{
          console.log(res);
          this.works=res;
          this.showList=true;
        },
        error=>{
          console.log(error);
        }
      )
    }else if(this.searchType==2){
            //------------------------------------------------------------------

      let phrase=0;
      
      if(this.isChecked2){
       phrase=1; 
      }

      this.searchService.searchByTitle(this.title,phrase).subscribe(
        res=>{
          console.log(res);
          this.works=res;
          this.showList=true;
        },
        error=>{
          console.log(error);
        }
      )
      
    }else if(this.searchType==3){
      //------------------------------------------------------------------

      let phrase=0;
      
      if(this.isChecked3){
       phrase=1; 
      }

      this.searchService.searchByKeyTerms(this.keyTerms,phrase).subscribe(
        res=>{
          console.log(res);
          this.works=res;
          this.showList=true;
        },
        error=>{
          console.log(error);
        }
      )
    }else if(this.searchType==4){
      //------------------------------------
      let phrase=0;
      
      if(this.isChecked4){
       phrase=1; 
      }

      this.searchService.searchByWorkContent(this.content,phrase).subscribe(
        res=>{
          console.log(res);
          this.works=res;
          this.showList=true;
        },
        error=>{
          console.log(error);
        }
      )
    }else if(this.searchType==5){
  //------------------------------------------------------------------

      console.log(this.scienceAreass[0]);
      let temp="";
      let i;
      for(i=0;i<this.scienceAreass.length;i++){
        temp=temp.concat(this.scienceAreass[i]);
       temp=temp.concat('-');
      }
    
      if(temp==""){
        alert("Oaberite naucne oblasti");
        return;
      }
      
      temp=temp.substring(0,temp.length-1);
       console.log('tusmooo'+temp);

       this.searchService.searchByScienceAreas(temp).subscribe(
         res=>{
          this.works=res;
          this.showList=true;
         },
         error=>{
           console.log(error);
         }
       )
      
    }else if(this.searchType==6){
      //------------------------------------------------------------------
      let phrase=0;
      
      if(this.isChecked6){
       phrase=1; 
      }

      this.searchService.searchByAuthors(this.authors,phrase).subscribe(
        res=>{
          console.log(res);
          this.works=res;
          this.showList=true;
        },
        error=>{
          console.log(error);
        }
      )
    }
  
  }

}
