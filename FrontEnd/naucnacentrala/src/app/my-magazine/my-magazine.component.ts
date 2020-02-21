import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CommonService } from '../services/Common/common.service';
import { MagazineDTO } from '../model/magazine-dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-magazine',
  templateUrl: './my-magazine.component.html',
  styleUrls: ['./my-magazine.component.scss']
})
export class MyMagazineComponent implements OnInit {

  private magazineForm:FormGroup;
  private magazine;
  private magazineDTO;
  private submitted=false;
  private success=false;

  constructor(private router:Router,private commonService:CommonService,private formBuilder:FormBuilder) { }

  ngOnInit() {


    this.magazineForm=this.formBuilder.group({
      name:['',Validators.required],
      issn:['',Validators.compose([Validators.required,Validators.minLength(3)])],
      membershipType: ['2',Validators.required],
    });

    this.commonService.getMyMagazine().subscribe(
      res=>{
        console.log(res);
        this.magazine=res;
        this.magazineForm.controls['issn'].setValue(this.magazine.issn);
        this.magazineForm.controls['name'].setValue(this.magazine.name);
        let mst='1';
        if(!this.magazine.openAccess){
          mst='2';
        }
        this.magazineForm.controls['membershipType'].setValue(mst);
      },
      error=>
      {
        console.log(error);
      }
    );

  }

  sendCorrection(){
    this.submitted=true;

    if(this.magazineForm.invalid){
      return;
    }

    this.success=true;
    
    let name=this.magazineForm.get('name').value;
    let issn=this.magazineForm.get('issn').value;
    let membershipType=this.magazineForm.get('membershipType').value;
    
    this.magazineDTO=new MagazineDTO(this.magazine.id,name,issn,[],membershipType,'',[],[]);
    console.log(this.magazineDTO);
    
    this.commonService.sendCorrection(this.magazineDTO).subscribe(
      res=>{
        alert('Casopis poslat na proveru');
        this.router.navigateByUrl('/');
    },
    error=>{
      console.log(error);
    });
  }

}
