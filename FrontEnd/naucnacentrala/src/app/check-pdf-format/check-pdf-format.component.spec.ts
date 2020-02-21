import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckPdfFormatComponent } from './check-pdf-format.component';

describe('CheckPdfFormatComponent', () => {
  let component: CheckPdfFormatComponent;
  let fixture: ComponentFixture<CheckPdfFormatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckPdfFormatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckPdfFormatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
