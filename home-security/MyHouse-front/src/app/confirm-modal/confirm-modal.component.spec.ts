import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmModal } from './confirm-modal.component';

describe('ConfirmModal', () => {
  let component: ConfirmModal;
  let fixture: ComponentFixture<ConfirmModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmModal ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
