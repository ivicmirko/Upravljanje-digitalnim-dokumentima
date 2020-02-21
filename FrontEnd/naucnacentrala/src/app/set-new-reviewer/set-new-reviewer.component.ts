import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RouterService } from '../services/Router/router.service';
import { EditorWorkService } from '../services/EditorWork/editor-work.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EnumValue } from '../model/enum-value';

@Component({
  selector: 'app-set-new-reviewer',
  templateUrl: './set-new-reviewer.component.html',
  styleUrls: ['./set-new-reviewer.component.scss']
})
export class SetNewReviewerComponent implements OnInit {

  private oldUsername="";
  private processId="";
  private setNewReviewerForm:FormGroup;

  private formFieldsDto = null;
  private formFields = [];

  private enumKeys=[];
  private enumValues = [];
  private enumObjects=[];
  private enumObject:EnumValue;

  private submitted=false;
  private success=false;

  constructor(private formBuilder:FormBuilder,private router:Router,private route:ActivatedRoute,private editorService:EditorWorkService) { 
    console.log('asasa');
    this.route.params.subscribe( params => {
      this.oldUsername = params.oldUsername; 
      this.processId = params.processId;
    });
    this.editorService.getSetNewReviewerForm(this.processId,this.oldUsername).subscribe(
      res=>{
        console.log('AAAAAAAAa');
        console.log(res);
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processId = res.processInstanceId;
        this.formFields.forEach( (field) =>{
            
          if(field.type.name=='enum'){
            this.enumKeys =Object.keys(field.properties);
            this.enumValues =Object.values(field.properties);
            for(let i=0; i<this.enumKeys.length; i++){
              this.enumObject=new EnumValue(this.enumKeys[i],this.enumValues[i]);
              this.enumObjects.push(this.enumObject);
            }
          }
        });
      },
      error=>{
        console.log('VVVVVVVVVVVVVV');

        console.log(error);
      }
    );

  }

  ngOnInit() {
    console.log('dasdasdasdas');
    this.editorService.getSetNewReviewerForm(this.processId,this.oldUsername).subscribe(
      res=>{
        console.log('AAAAAAAAa');
        console.log(res);
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processId = res.processInstanceId;
        this.formFields.forEach( (field) =>{
            
          if(field.type.name=='enum'){
            this.enumKeys =Object.keys(field.properties);
            this.enumValues =Object.values(field.properties);
            for(let i=0; i<this.enumKeys.length; i++){
              this.enumObject=new EnumValue(this.enumKeys[i],this.enumValues[i]);
              this.enumObjects.push(this.enumObject);
            }
          }
        });
      },
      error=>{
        console.log('VVVVVVVVVVVVVV');

        console.log(error);
      }
    );

    this.setNewReviewerForm=this.formBuilder.group({
      newrew:['',Validators.required]
    });

    
    
  }

  chooseNewReviewer(value,form){
    this.submitted=true;

    if(this.setNewReviewerForm.invalid){
      return;
    }

    this.success=true;

    let o = new Array();
    let temp="";
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      
      if(property == 'newrew'){
        o.push({fieldId : 'newRew', fieldValue : value[property]});
      }else{
        o.push({fieldId : property, fieldValue : value[property]});

      }
    }


    console.log(o);

    this.editorService.postSetNewReviewerForm(this.oldUsername,this.processId,o).subscribe(
      res=>{
        this.router.navigate(['/']);
      },
      error=>{
        console.log(error);
      }
    );
    

  }
}
