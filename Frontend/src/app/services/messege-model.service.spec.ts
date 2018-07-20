import { TestBed, inject } from '@angular/core/testing';

import { MessegeModelService } from './messege-model.service';

describe('MessegeModelService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MessegeModelService]
    });
  });

  it('should be created', inject([MessegeModelService], (service: MessegeModelService) => {
    expect(service).toBeTruthy();
  }));
});
