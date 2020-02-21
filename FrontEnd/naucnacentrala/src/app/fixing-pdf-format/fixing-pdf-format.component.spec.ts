import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FixingPdfFormatComponent } from './fixing-pdf-format.component';

describe('FixingPdfFormatComponent', () => {
  let component: FixingPdfFormatComponent;
  let fixture: ComponentFixture<FixingPdfFormatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FixingPdfFormatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FixingPdfFormatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
