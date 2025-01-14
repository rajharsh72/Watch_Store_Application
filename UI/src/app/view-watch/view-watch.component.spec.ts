import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewWatchComponent } from './view-watch.component';

describe('ViewWatchComponent', () => {
  let component: ViewWatchComponent;
  let fixture: ComponentFixture<ViewWatchComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewWatchComponent]
    });
    fixture = TestBed.createComponent(ViewWatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
