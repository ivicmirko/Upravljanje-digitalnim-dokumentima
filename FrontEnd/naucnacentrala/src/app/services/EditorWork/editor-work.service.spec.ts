import { TestBed } from '@angular/core/testing';

import { EditorWorkService } from './editor-work.service';

describe('EditorWorkService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EditorWorkService = TestBed.get(EditorWorkService);
    expect(service).toBeTruthy();
  });
});
