import { TestBed } from '@angular/core/testing';

import { ReviewerService } from './reviewer.service';

describe('ReviewerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReviewerService = TestBed.get(ReviewerService);
    expect(service).toBeTruthy();
  });
});
