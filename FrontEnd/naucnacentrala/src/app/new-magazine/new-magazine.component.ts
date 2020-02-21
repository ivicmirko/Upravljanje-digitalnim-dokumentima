import { Component, OnInit } from '@angular/core';
import { EnumValue } from '../model/enum-value';
import { NewMagazineService } from '../services/NewMagazine/new-magazine.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonService } from '../services/Common/common.service';
import { MagazineDTO } from '../model/magazine-dto';
import { SessionStorageService } from '../services/SessionStorage/session-storage.service';
import { ProfileDTO } from '../model/profile-dto';

@Component({
  selector: 'app-new-magazine',
  templateUrl: './new-magazine.component.html',
  styleUrls: ['./new-magazine.component.scss']
})
export class NewMagazineComponent implements OnInit {

  private submitted=false;
  private submittedEB=false;
  private success=false;
  private magazineForm:FormGroup;
  private editorialBoardForm:FormGroup;
  private magazineDTO:MagazineDTO;
  private selectedPayment:String;
  private selectedSA=[];
  private selectedEditors=[];
  private selectedReviewers=[];
  private firstPhaseSuccessed=false;
  private scienceAreas=[];
  private reviewers=[];
  private editors=[];
  
  private repeated_password = "";
  private categories = [];
  private formFieldsDto = null;
  private formFields = [];
  private choosen_category = -1;
  private processInstance = "";
  private enumValues = [];
  private tasks = [];
  private enumKeys=[];
  private enumObjects=[];
  private enumObject:EnumValue;
  private enumHere=false;

  private enumValues1 = [];
  private enumKeys1=[];
  private enumObjects1=[];
  private enumObject1:EnumValue;

   constructor(private commonService:CommonService, private sessionStorageService:SessionStorageService,//private userService : UserService, private repositoryService : RepositoryService,
    private newMagazineService:NewMagazineService, private router:Router, private formBuilder :FormBuilder) {
    
   }

  ngOnInit() {
    this.magazineForm=this.formBuilder.group({
      name:['',Validators.required],
      issn:['',Validators.compose([Validators.required,Validators.minLength(3)])],
      membershipType: ['1',Validators.required],
      scienceAreas:['',Validators.required]
      // scienceAreas:[[]]
    });

    this.editorialBoardForm=this.formBuilder.group({
      editors:[''],
      reviewers:['',Validators.minLength(2)]
    });

    this.commonService.getAllScienceAreas().subscribe(
      res=>{
        this.scienceAreas=res;
      },
      error=>{
        console.log(error);
      })
  }

  sendMagazineData(){
    this.submitted=true;

    if(this.magazineForm.invalid){
      return;
    }

    this.success=true;
    
    let name=this.magazineForm.get('name').value;
    let issn=this.magazineForm.get('issn').value;
    let membershipType=this.magazineForm.get('membershipType').value;
    let scienceAreas=this.magazineForm.get('scienceAreas').value;

    let username=this.sessionStorageService.getPrfile().username;
    
    this.magazineDTO=new MagazineDTO(0,name,issn,scienceAreas,membershipType,username,[],[]);

    this.newMagazineService.sendMagazineData(this.magazineDTO).subscribe(
      res=>{
        this.magazineDTO.id=res.id;
        this.commonService.getSAReviewers(this.magazineForm.get('scienceAreas').value,).subscribe(
          res=>{
            this.reviewers=res;
        },
        error=>{
          console.log(error);
        });
    
        this.commonService.getFreeEditors().subscribe(
          res=>{
            this.editors=res;
          },
          error=>{
            console.log(error);
          }
        );
    },
    error=>{
      console.log(error);
    });

    this.firstPhaseSuccessed=true;
  }

  sendMagazineEditorialBoard(){

    this.submitted=true;

    if(this.magazineForm.invalid){
      return;
    }

    this.success=true;
    this.magazineDTO.editors=this.editorialBoardForm.get('editors').value;
    this.magazineDTO.reviewers=this.editorialBoardForm.get('reviewers').value;

    console.log(this.magazineDTO);
    this.newMagazineService.sendEditorialBoard(this.magazineDTO).subscribe(
      res=>{
        alert('Uspesno dodat casopis');
        this.router.navigate(['/']);
      },
      error=>{
        console.log(error);
      }
    )
  }

  

//   onSubmit(value, form){
//     let o = new Array();
//     for (var property in value) {
//       console.log(property);
//       console.log(value[property]);
      
//       o.push({fieldId : property, fieldValue : value[property]});
//     }

//     // let temp="";
//     // for(let i=0; i < this.selectedValues.length ;i++){
//     //   temp=temp.concat(this.selectedValues[i]);
//     //   temp=temp.concat(',');
//     //   console.log(temp);
//     // }
//     // if(temp==""){
//     //   alert("Oaberite naucne oblasti");
//     //   return;
//     // }
//     temp=temp.substring(0,temp.length-1);
//     console.log('tusmooo'+temp);
//     o.push({fieldId : "formScienceArea1", fieldValue : temp});
//     console.log('------------------------------------------------------------------------');
//     // o.push({fieldId : "enumerationField", fieldValue : this.item);
//     console.log("iteem="+temp);
//    console.log(o);
//     this.newMagazineService.sendData(o, this.formFieldsDto.taskId).subscribe(
//       res => {
//         //console.log(res);
//         console.log('imda l staaaa');
//         //alert("Vasi podaci su poslati! Proverite mail i potvrdite registraciju");
//         this.router.navigate(['/']);
//       },
//       err => {
//         console.log(err);
//         alert("Greska"+err);
//       }
//     );
//     //this.router.navigate(['/']);

//   }

//    onChange($event) {
//     console.log(this.selectedValues);
//     // I want to do something here for new selectedDevice, but what I
//     // got here is always last selection, not the one I just select.
// }
onChangePayment($event){}
}
