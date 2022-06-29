import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardObjectsComponent } from './board-objects.component';

describe('BoardObjectsComponent', () => {
  let component: BoardObjectsComponent;
  let fixture: ComponentFixture<BoardObjectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardObjectsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardObjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
