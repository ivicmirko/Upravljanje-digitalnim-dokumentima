import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MakingDecisionComponent } from './making-decision.component';

describe('MakingDecisionComponent', () => {
  let component: MakingDecisionComponent;
  let fixture: ComponentFixture<MakingDecisionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MakingDecisionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MakingDecisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
