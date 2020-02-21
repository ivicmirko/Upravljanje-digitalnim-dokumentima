import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewingWorkComponent } from './reviewing-work.component';

describe('ReviewingWorkComponent', () => {
  let component: ReviewingWorkComponent;
  let fixture: ComponentFixture<ReviewingWorkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewingWorkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewingWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
