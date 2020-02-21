import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyMagazineComponent } from './my-magazine.component';

describe('MyMagazineComponent', () => {
  let component: MyMagazineComponent;
  let fixture: ComponentFixture<MyMagazineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyMagazineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyMagazineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
