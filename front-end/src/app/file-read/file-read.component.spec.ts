import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FileReadComponent } from './file-read.component';

describe('FileReadComponent', () => {
  let component: FileReadComponent;
  let fixture: ComponentFixture<FileReadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FileReadComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FileReadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
