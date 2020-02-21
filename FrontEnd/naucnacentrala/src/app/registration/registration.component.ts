import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/Auth/auth.service';
import { RegistrationService } from '../services/Registration/registration.service';
import { EnumValue } from '../model/enum-value';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  selectedValues=[]
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

   constructor(//private userService : UserService, private repositoryService : RepositoryService,
    private registrationService:RegistrationService, private router:Router) {
    
    registrationService.startProcess().subscribe(
      res => {
        console.log(res);
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.formFields.forEach( (field) =>{
          
          if(field.type.name=='enum'){
            console.log('usaoo');
            this.enumKeys =Object.keys(field.properties);
            this.enumValues =Object.values(field.properties);
            console.log(this.enumKeys);
            for(let i=0; i<this.enumKeys.length; i++){
              this.enumObject=new EnumValue(this.enumKeys[i],this.enumValues[i]);
              this.enumObjects.push(this.enumObject);
            }
            console.log(this.enumObjects);
            this.enumHere=true;
            console.log(this.enumValues);
          }
        });
      },
      err => {
        console.log(err);
      }
    );
   }

  ngOnInit() {
  }

  onSubmit(value, form){
    let o = new Array();
    let temp="";
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      
      if(property != 'formScienceArea'){
        if((value[property] == '')){
          if(property == 'formReviewer')
            value[property] = false;
          else
            value[property] = null;
        }
        o.push({fieldId : property, fieldValue : value[property]});
      }else{
        for(var pom of value[property]){
          temp=temp.concat(pom);
          temp=temp.concat(',');
          
        }
      }

    }
    temp=temp.substring(0,temp.length-1);
    o.push({fieldId : "formScienceArea1", fieldValue : temp});

    console.log(o);

    // let temp="";
    // for(let i=0; i < this.selectedValues.length ;i++){
    //   temp=temp.concat(this.selectedValues[i]);
    //   temp=temp.concat(',');
    //   console.log(temp);
    // }
    if(temp==""){
      alert("Oaberite naucne oblasti");
      return;
    }
    // temp=temp.substring(0,temp.length-1);
    // console.log('tusmooo'+temp);
    // o.push({fieldId : "formScienceArea1", fieldValue : temp});
    console.log('------------------------------------------------------------------------');
    // o.push({fieldId : "enumerationField", fieldValue : this.item);
    // console.log("iteem="+temp);


    this.registrationService.registerUser(o, this.formFieldsDto.taskId,this.processInstance).subscribe(
      res => {
        //console.log(res);
        // console.log('imda l staaaa');
        //alert("Vasi podaci su poslati! Proverite mail i potvrdite registraciju");
        this.router.navigate(['/']);
      },
      err => {
      
        alert("Neispravni email ili username");
      }
    );
    //this.router.navigate(['/']);

  }

  // getTasks(){
  //   let x = this.repositoryService.getTasks(this.processInstance);

  //   x.subscribe(
  //     res => {
  //       console.log(res);
  //       this.tasks = res;
  //     },
  //     err => {
  //       console.log("Error occured");
  //     }
  //   );
  //  }

  //  claim(taskId){
  //   let x = this.repositoryService.claimTask(taskId);

  //   x.subscribe(
  //     res => {
  //       console.log(res);
  //     },
  //     err => {
  //       console.log("Error occured");
  //     }
  //   );
  //  }

  //  complete(taskId){
  //   let x = this.repositoryService.completeTask(taskId);

  //   x.subscribe(
  //     res => {
  //       console.log(res);
  //       this.tasks = res;
  //     },
  //     err => {
  //       console.log("Error occured");
  //     }
  //   );
  //  }

   onChange($event) {
    console.log(this.selectedValues);
    // I want to do something here for new selectedDevice, but what I
    // got here is always last selection, not the one I just select.
}

}
