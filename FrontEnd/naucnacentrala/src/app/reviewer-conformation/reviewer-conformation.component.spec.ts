import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewerConformationComponent } from './reviewer-conformation.component';

describe('ReviewerConformationComponent', () => {
  let component: ReviewerConformationComponent;
  let fixture: ComponentFixture<ReviewerConformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewerConformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewerConformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
