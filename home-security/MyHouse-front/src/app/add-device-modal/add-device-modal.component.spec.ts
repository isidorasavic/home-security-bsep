import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDeviceModal } from './add-device-modal.component';

describe('AddDeviceModal', () => {
  let component: AddDeviceModal;
  let fixture: ComponentFixture<AddDeviceModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDeviceModal ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDeviceModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
