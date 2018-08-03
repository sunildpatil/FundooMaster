import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNoteTemplateComponent } from './create-note-template.component';

describe('CreateNoteTemplateComponent', () => {
  let component: CreateNoteTemplateComponent;
  let fixture: ComponentFixture<CreateNoteTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateNoteTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateNoteTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
