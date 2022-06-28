import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeObjectOwnerModal } from './change-object-owner-modal.component';

describe('ChangeObjectOwnerModal', () => {
  let component: ChangeObjectOwnerModal;
  let fixture: ComponentFixture<ChangeObjectOwnerModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeObjectOwnerModal ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeObjectOwnerModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
