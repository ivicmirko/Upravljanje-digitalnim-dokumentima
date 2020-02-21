import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ActivationComponent } from './activation/activation.component';
import { ReviewerConformationComponent } from './reviewer-conformation/reviewer-conformation.component';
import { NewMagazineComponent } from './new-magazine/new-magazine.component';
import { MagazineOverviewComponent } from './magazine-overview/magazine-overview.component';
import { MyMagazineComponent } from './my-magazine/my-magazine.component';
import { AddNewWorkComponent } from './add-new-work/add-new-work.component';
import { ArrivedWorksComponent } from './arrived-works/arrived-works.component';
import { CheckPdfFormatComponent } from './check-pdf-format/check-pdf-format.component';
import { FixingPdfFormatComponent } from './fixing-pdf-format/fixing-pdf-format.component';
import { AddWorkReviewersComponent } from './add-work-reviewers/add-work-reviewers.component';
import { ReviewingWorkComponent } from './reviewing-work/reviewing-work.component';
import { MakingDecisionComponent } from './making-decision/making-decision.component';
import { FixWorkComponent } from './fix-work/fix-work.component';
import { MyWorksComponent } from './my-works/my-works.component';
import { SetNewReviewerComponent } from './set-new-reviewer/set-new-reviewer.component';
import { SearchComponent } from './search/search.component';
import { AdvancedSearchComponent } from './advanced-search/advanced-search.component';


const routes: Routes = [
  {path: 'login', component:LoginComponent},
  {path: 'registration', component:RegistrationComponent},
  {
    path: "activation/:username/:processId",
    component: ActivationComponent
  },
  {path: 'reviewerRequests', component:ReviewerConformationComponent},
  {path: 'newmagazine', component:NewMagazineComponent},
  {path: 'newMagazinesRequests', component:MagazineOverviewComponent},
  {path: 'myMagazine', component:MyMagazineComponent},
  {path: 'addNewWork', component:AddNewWorkComponent},
  {path: 'arrivedWorks', component:ArrivedWorksComponent},
  {path: 'checkPDFWorks', component:CheckPdfFormatComponent},
  {path: 'fixingPdfFormat', component:FixingPdfFormatComponent},
  {path: 'addWorkReviewers', component:AddWorkReviewersComponent},
  {path: 'reviewingWorks', component:ReviewingWorkComponent},
  {path: 'reviewedWorks', component:MakingDecisionComponent},
  {path: 'worksForFix', component:FixWorkComponent},
  {path: 'myWorks', component:MyWorksComponent},
  {
    path: "setNewReviewer/:oldUsername/:processId",
    component: SetNewReviewerComponent
  },
  {
    path: "search/:searchType",
    component: SearchComponent
  },
  {path: 'advancedSearch', component:AdvancedSearchComponent}



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
