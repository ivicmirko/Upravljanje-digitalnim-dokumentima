import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { WorkService } from '../services/Work/work.service';
import { EnumValue } from '../model/enum-value';
import { SessionStorageService } from '../services/SessionStorage/session-storage.service';

@Component({
  selector: 'app-add-new-work',
  templateUrl: './add-new-work.component.html',
  styleUrls: ['./add-new-work.component.scss']
})
export class AddNewWorkComponent implements OnInit {

  private choosingMagazineForm: FormGroup;
  private inputDetailsOfWorkForm:FormGroup;
  private coAuthorForm:FormGroup;
  private submitted = false;
  private success = false;

  private workId:Number;
  private magazineId:Number;
  private magazines=[];
  private scienceAreasOfMagazine=[];


  private enumKeys=[];
  private enumValues = [];
  private enumObjects=[];
  private enumObject:EnumValue;


  private enumKeys2=[];
  private enumValues2 = [];
  private enumObjects2=[];
  private enumObject2:EnumValue;

  private submitted2 = false;
  private success2 = false;

  private submitted3 = false;
  private success3 = false;
  private allCoAuthors=0;
  private numOfCoAuthors=0;

  pdfFile:File=null;


  private firstPhaseFinished=false;
  private showCoauthors=false;

  constructor(private sessionStorageService:SessionStorageService,private formBuilder:FormBuilder, private router:Router, private workServcie:WorkService ) {

      this.workServcie.getAllMagazines().subscribe(
        res=>{
          console.log(res);
          this.magazines=res;
        },
        error=>{
          console.log(error);
        }
      );
     }

  ngOnInit() {
    this.choosingMagazineForm=this.formBuilder.group({
      chooseMagazineForm:['',Validators.required]
    });

    this.inputDetailsOfWorkForm=this.formBuilder.group({
      titleForm: ['',Validators.required],
      abstractWorkForm: ['', Validators.required],
      keyTermsForm: ['',Validators.required],
      scienceAreaForm: ['',Validators.required],
      numberCoauthorsForm: ['0',Validators.required],
      pdfWorkForm: ['',Validators.required]
    });

    this.coAuthorForm=this.formBuilder.group({
      nameCoForm:['',Validators.required],
      surnameCoForm:['',Validators.required],
      emailCoForm: ['',Validators.required],
      countryCoForm: ['', Validators.required],
      cityCoForm: ['',Validators.required],
      longitudeForm: [0, Validators.required],
      latitudeForm: [0,Validators.required]
    });
  }

  chooseMagazine(value, form){
    this.submitted=true;

    if(this.choosingMagazineForm.invalid){
      return;
    }

    this.success=true;

    this.magazineId=this.choosingMagazineForm.get('chooseMagazineForm').value;


    this.workServcie.setWorkMagazine(this.magazineId).subscribe(
      res=>{
        this.firstPhaseFinished=true;
        this.showCoauthors=false;
        this.scienceAreasOfMagazine=res;
      },
      error=>{
        console.log(error);
      }
    )
  }

  sendMagazineData(value,form){
    this.submitted2=true;

    if(this.inputDetailsOfWorkForm.invalid){
      return;
    }

    this.success2=true;

    this.numOfCoAuthors=this.inputDetailsOfWorkForm.get('numberCoauthorsForm').value;
    this.allCoAuthors=this.numOfCoAuthors;
    let o;
    o={
      title: this.inputDetailsOfWorkForm.get('titleForm').value,
      workAbstract: this.inputDetailsOfWorkForm.get('abstractWorkForm').value,
      keyTerms: this.inputDetailsOfWorkForm.get('keyTermsForm').value,
      scienceArea: this.inputDetailsOfWorkForm.get('scienceAreaForm').value
    }
    console.log(o);

    this.workServcie.setWorkDetails(this.magazineId,o).subscribe(
      res=>{
        this.workId=res.id;
        this.workServcie.sendFile(this.pdfFile,this.workId).subscribe(
          res=>{
            if(this.numOfCoAuthors != 0){
                this.showCoauthors=true;
                this.firstPhaseFinished=false;
                this.getCoAuthorsForm();
              }else{
                alert('Vas rad je poslat na razmatranje.');
                this.router.navigate(['/']);
              }
          },
          error=>{
            console.log(error);
          }
        )
      },
      error=>{
        console.log(error);
      }
      
    )
  }

  // sendMagazineData(value, form){
  //   this.submitted2=true;

  //   if(this.inputDetailsOfWorkForm.invalid){
  //     return;
  //   }

  //   this.success2=true;

  //   let o = new Array();

  //   for (var property in value) {
  //     if(property == 'scienceAreaForm'){
  //       o.push({fieldId : 'scienceAreaForma', fieldValue : value[property]});
  //     }else if(property == 'pdfWorkForm'){
  //       //o.push({fieldId : 'pdfWorkForma', fieldValue : this.pdfFile});
  //     }else{
  //       o.push({fieldId : property, fieldValue : value[property]});
  //     }
  //   }

  //   this.numOfCoAuthors=this.inputDetailsOfWorkForm.get('numberCoauthorsForm').value;
  //   console.log('Num of coauthors'+this.numOfCoAuthors);
  //   this.workServcie.postFormForWorkDetails(this.processInstance,this.formFieldsDto.taskId,o).subscribe(
  //     res=>{
  //       if(res.message == 'ok'){
  //         this.workServcie.sendFile(this.pdfFile,this.processInstance).subscribe(
  //           res=>{
  //           console.log('Num2 of coauthors'+this.numOfCoAuthors);
  
  //             if(this.numOfCoAuthors != 0){
  //               this.showCoauthors=true;
  //               this.firstPhaseFinished=false;
  //               this.getCoAuthorsForm();
  //             }else{
  //               alert('Vas rad je poslat na razmatranje.');
  //               this.router.navigate(['/']);
  //             }
              
  //           },
  //           error=>
  //           {
  //             console.log(error);
  //           }
  //         )
  //       }else{
  //         alert("Greska");
  //       }
  //     },
  //     error=>{
  //       console.log(error);
  //     }
  //   )
  // }

  pdfSelected(event){
    this.pdfFile=<File>event.target.files[0];
    console.log(event);
  }

  getCoAuthorsForm(){

    this.numOfCoAuthors=this.numOfCoAuthors-1;     
    
  }

  postCoauthor(value, form){

    this.submitted3=true;

    if(this.coAuthorForm.invalid){
      return;
    }

    this.success3=true;

    let o;

    o={
      name: this.coAuthorForm.get('nameCoForm').value,
      surname: this.coAuthorForm.get('surnameCoForm').value,
      email: this.coAuthorForm.get('emailCoForm').value,
      country: this.coAuthorForm.get('countryCoForm').value,
      city: this.coAuthorForm.get('cityCoForm').value,
      latitude: this.coAuthorForm.get('latitudeForm').value,
      longitude: this.coAuthorForm.get('longitudeForm').value,
    }

    this.workServcie.addCoAuthor(this.workId,o).subscribe(
      res=>{
        if(this.numOfCoAuthors > 0){
          this.submitted3=false;
          this.coAuthorForm.reset();
          this.getCoAuthorsForm();
        }else{
          alert('Dodali ste koautore');
          this.router.navigate(['/']);
        }
      },
      error=>{
        console.log(error);
      }
    );
  }


}