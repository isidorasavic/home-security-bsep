import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddObjectModal } from './add-object-modal.component';

describe('AddObjectModal', () => {
  let component: AddObjectModal;
  let fixture: ComponentFixture<AddObjectModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddObjectModal ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddObjectModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
