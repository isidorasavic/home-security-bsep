import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportModal } from './report-modal.component';

describe('ReportModal', () => {
  let component: ReportModal;
  let fixture: ComponentFixture<ReportModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportModal ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
