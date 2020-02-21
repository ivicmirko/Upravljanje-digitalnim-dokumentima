import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MagazineOverviewComponent } from './magazine-overview.component';

describe('MagazineOverviewComponent', () => {
  let component: MagazineOverviewComponent;
  let fixture: ComponentFixture<MagazineOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MagazineOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MagazineOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
