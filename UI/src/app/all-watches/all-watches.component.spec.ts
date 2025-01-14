import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllWatchesComponent } from './all-watches.component';

describe('AllWatchesComponent', () => {
  let component: AllWatchesComponent;
  let fixture: ComponentFixture<AllWatchesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllWatchesComponent]
    });
    fixture = TestBed.createComponent(AllWatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
