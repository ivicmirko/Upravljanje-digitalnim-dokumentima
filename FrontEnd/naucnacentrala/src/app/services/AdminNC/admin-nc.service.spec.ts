import { TestBed } from '@angular/core/testing';

import { AdminNCService } from './admin-nc.service';

describe('AdminNCService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdminNCService = TestBed.get(AdminNCService);
    expect(service).toBeTruthy();
  });
});
