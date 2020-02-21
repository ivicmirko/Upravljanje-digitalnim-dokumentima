import { TestBed } from '@angular/core/testing';

import { AuthorWorkService } from './author-work.service';

describe('AuthorWorkService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthorWorkService = TestBed.get(AuthorWorkService);
    expect(service).toBeTruthy();
  });
});
