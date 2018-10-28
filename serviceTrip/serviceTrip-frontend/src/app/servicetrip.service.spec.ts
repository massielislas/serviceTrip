import { TestBed, inject } from '@angular/core/testing';

import { ServicetripService } from './servicetrip.service';

describe('ServicetripService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServicetripService]
    });
  });

  it('should be created', inject([ServicetripService], (service: ServicetripService) => {
    expect(service).toBeTruthy();
  }));
});
