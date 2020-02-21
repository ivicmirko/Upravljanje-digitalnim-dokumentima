import { Component, OnInit } from '@angular/core';
import { AuthorWorkService } from '../services/AuthorWork/author-work.service';

@Component({
  selector: 'app-my-works',
  templateUrl: './my-works.component.html',
  styleUrls: ['./my-works.component.scss']
})
export class MyWorksComponent implements OnInit {

  private works=[];
  constructor(private authorService:AuthorWorkService) { }

  ngOnInit() {

    this.authorService.getMyApprovedWorks().subscribe(
      res=>{
        this.works=res;
      },
      error=>{
        console.log(error);
      }
    )
  }

}
