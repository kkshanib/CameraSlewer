import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CameraSlewerComponent } from './camera-slewer.component';

describe('CameraSlewerComponent', () => {
  let component: CameraSlewerComponent;
  let fixture: ComponentFixture<CameraSlewerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CameraSlewerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CameraSlewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
