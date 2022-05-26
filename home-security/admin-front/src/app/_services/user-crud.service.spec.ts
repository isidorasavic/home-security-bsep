import { TestBed } from '@angular/core/testing';

import { UserCRUDService } from './user-crud.service';

describe('UserCRUDService', () => {
  let service: UserCRUDService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserCRUDService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
