import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FixWorkComponent } from './fix-work.component';

describe('FixWorkComponent', () => {
  let component: FixWorkComponent;
  let fixture: ComponentFixture<FixWorkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FixWorkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FixWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
