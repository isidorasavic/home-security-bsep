import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTenantModal } from './add-tenant-modal.component';

describe('BoardUserComponent', () => {
  let component: AddTenantModal;
  let fixture: ComponentFixture<AddTenantModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTenantModal ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTenantModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
