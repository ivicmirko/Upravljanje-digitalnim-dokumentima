import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
//import { PdfViewerModule } from 'ng2-pdf-viewer';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { TokenInterceptorService } from './services/TokenInterceptor/token-interceptor.service';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ActivationComponent } from './activation/activation.component';
import { ReviewerConformationComponent } from './reviewer-conformation/reviewer-conformation.component';
import { NewMagazineComponent } from './new-magazine/new-magazine.component';
import { MyMagazineComponent } from './my-magazine/my-magazine.component';
import { MagazineOverviewComponent } from './magazine-overview/magazine-overview.component';
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

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    RegistrationComponent,
    ActivationComponent,
    ReviewerConformationComponent,
    NewMagazineComponent,
    MyMagazineComponent,
    MagazineOverviewComponent,
    AddNewWorkComponent,
    ArrivedWorksComponent,
    CheckPdfFormatComponent,
    FixingPdfFormatComponent,
    AddWorkReviewersComponent,
    ReviewingWorkComponent,
    MakingDecisionComponent,
    FixWorkComponent,
    MyWorksComponent,
    SetNewReviewerComponent,
    SearchComponent,
    AdvancedSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
   // PdfViewerModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true,
  }
],
  bootstrap: [AppComponent]
})
export class AppModule { }
