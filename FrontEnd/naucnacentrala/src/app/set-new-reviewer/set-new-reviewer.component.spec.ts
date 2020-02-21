import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SetNewReviewerComponent } from './set-new-reviewer.component';

describe('SetNewReviewerComponent', () => {
  let component: SetNewReviewerComponent;
  let fixture: ComponentFixture<SetNewReviewerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SetNewReviewerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetNewReviewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
