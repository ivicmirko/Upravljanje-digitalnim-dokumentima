import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWorkReviewersComponent } from './add-work-reviewers.component';

describe('AddWorkReviewersComponent', () => {
  let component: AddWorkReviewersComponent;
  let fixture: ComponentFixture<AddWorkReviewersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddWorkReviewersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddWorkReviewersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
