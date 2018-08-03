import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { NoteService } from '../../services/note.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-create-note-template',
  templateUrl: './create-note-template.component.html',
  styleUrls: ['./create-note-template.component.css']
})
export class CreateNoteTemplateComponent implements OnInit {

  constructor(private noteService: NoteService) { }

  records: any = [];
  showCard = false;
  title = "";
  description = "";

  @Output() refresher = new EventEmitter<string>();

  refresh() {
    this.refresher.emit('complete');
  }

  ngOnInit() { }

  createNote(form) {
    this.noteService.create(form)
      .subscribe(
        (data) => {
          console.log(data);
          this.refresh();
        },
        response => {
          console.log(response);
        }
      )
  }

  toggleShowCard(form: NgForm) {
    this.showCard = !this.showCard;
    if (this.title == "" && this.description == "") {
      //skip
    }
    else
      this.createNote(form);
  }
}
