import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArrivedWorksComponent } from './arrived-works.component';

describe('ArrivedWorksComponent', () => {
  let component: ArrivedWorksComponent;
  let fixture: ComponentFixture<ArrivedWorksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArrivedWorksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArrivedWorksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
