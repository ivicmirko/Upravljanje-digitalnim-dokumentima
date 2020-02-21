import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewWorkComponent } from './add-new-work.component';

describe('AddNewWorkComponent', () => {
  let component: AddNewWorkComponent;
  let fixture: ComponentFixture<AddNewWorkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewWorkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
