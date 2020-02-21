import { TestBed } from '@angular/core/testing';

import { NewMagazineService } from './new-magazine.service';

describe('NewMagazineService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NewMagazineService = TestBed.get(NewMagazineService);
    expect(service).toBeTruthy();
  });
});
