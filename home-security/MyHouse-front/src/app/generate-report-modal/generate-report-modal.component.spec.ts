import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateReportModal } from './generate-report-modal.component';

describe('GenerateReportModal', () => {
  let component: GenerateReportModal;
  let fixture: ComponentFixture<GenerateReportModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenerateReportModal ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerateReportModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
