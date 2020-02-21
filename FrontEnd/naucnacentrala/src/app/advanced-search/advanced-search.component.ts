import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { CommonService } from '../services/Common/common.service';
import { SearchService } from '../services/Search/search.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-advanced-search',
  templateUrl: './advanced-search.component.html',
  styleUrls: ['./advanced-search.component.scss']
})
export class AdvancedSearchComponent implements OnInit {

  private searchForm:FormGroup;
  private searchType=0;
  private showList=false;
  private works=[];
  private scienceAreas=[];

  private magazineCheck=true;
  private titleCheck=true;
  private contentCheck=true;
  private keyTermsCheck=true;
  private authorsCheck=true;
  private isChecked6=true;
  private title="";
  private magazineName="";
  private keyTerms="";
  private authors="";
  private scienceAreass="";
  private content="";

  constructor(private searchService:SearchService,private router:Router,private route:ActivatedRoute,private formBuilder:FormBuilder,
    private commonService:CommonService) { }

  
  
  ngOnInit() {

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
    console.log(this.magazineCheck);

    let temp="";
    let i;
    for(i=0;i<this.scienceAreass.length;i++){
      temp=temp.concat(this.scienceAreass[i]);
      temp=temp.concat('-');
    }
      
    temp=temp.substring(0,temp.length-1);
    console.log('tusmooo'+temp);

    let o ;
    o={
      magazineName:this.magazineName,
      magazineCheck:this.magazineCheck,
      title:this.title,
      titleCheck:this.titleCheck,
      keyTerms:this.keyTerms,
      keyTermsCheck:this.keyTermsCheck,
      content:this.content,
      contentCheck:this.contentCheck,
      authors:this.authors,
      authorsCheck:this.authorsCheck,
      scienceAreass:temp,
    }

    console.log(o);
    this.searchService.advancedSearch(o).subscribe(
      res=>{
        console.log(res);
        this.works=res;
        this.showList=true;
      },
      error=>{
        console.log(error);
      }
    );

  }

}
