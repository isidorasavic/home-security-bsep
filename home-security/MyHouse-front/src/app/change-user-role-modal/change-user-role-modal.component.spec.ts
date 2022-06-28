import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeUserRoleModal } from './change-user-role-modal.component';

describe('ChangeUserRoleModal', () => {
  let component: ChangeUserRoleModal;
  let fixture: ComponentFixture<ChangeUserRoleModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeUserRoleModal ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeUserRoleModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
