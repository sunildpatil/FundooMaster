import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MatDatepickerInputEvent, MatDialog } from '@angular/material';
import { HttpService } from '../../services/http.service';
import { NoteService } from '../../services/note.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { NoteComponent } from '../note/note.component';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.css']
})
export class TemplateComponent implements OnInit {

  constructor(private noteService: NoteService) { }

  @Input() record;
  @Input() archiveIcon;
  token = "";
  showCard = false;
  title = "";
  description = "";
  
  reminderDate: String = "";
  reminderTime: String = "";
  reminderFrequency: String = "";

  @Output() refresher = new EventEmitter<string>();

  refresh() {
    this.refresher.emit('complete');
  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.reminderDate = (event.value.getMonth() + 1) + '/' + event.value.getDate() + '/' + event.value.getFullYear();
    console.log(this.reminderDate);
  }

  ngOnInit() {    
  }

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

  delete(noteId) {
    this.noteService.delete(noteId)
      .subscribe(
        (data) => {
          console.log(data);
          this.refresh();
        },
        response => {
          console.log(response.error);
        }
      )
  }

  update(note) {
    this.noteService.update(note)
      .subscribe(
        (data) => {
          console.log(data);
          this.refresh();
        },
        response => {
          console.log(response.error);
        }
      )
  }

  archiveNote(noteId) {
    this.noteService.archiveNote(noteId)
      .subscribe(
        (data) => {
          console.log(data);
          this.refresh();
        },
        response => {
          console.log(response.error);
        }
      );
  }

  changeColor(color, noteId) {
    this.noteService.changeColor(color, noteId)
      .subscribe(
        (data) => {
          console.log(data);
          this.refresh();
        },
        response => {
          console.log(response.error);
        }
      );
  }

  toggleShowCard(form: NgForm) {
    this.showCard = !this.showCard;
    if (this.title == "" && this.description == "") {
      //skip
    }
    else
      this.createNote(form);
  }

  openDialog(data) {
    this.noteService.openDialog(data)
      .subscribe(
        (value) => {
          try {
            value.noteId;
            this.update(value);
          } catch (error) {

          }
        }
      );
  }

  dateChk() {
    var today = new Date();
    var nextweek = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 8);
    var s = nextweek.getDay();
    console.log(s);
  }

  addReminder(noteId) {
    if (this.reminderDate == "" || this.reminderTime == "" || this.reminderFrequency == "") {
      //skip
    } else {
      this.noteService.addReminder(noteId, this.reminderDate, this.reminderTime, this.reminderFrequency)
        .subscribe(
          (data) => {
            console.log(data);
            this.refresh();
          },
          response => {
            console.log(response);
          }
        );
    }
  }

  deleteReminder(noteId) {
    console.log(noteId);
    this.noteService.deleteReminder(noteId)
    .subscribe(
      (data) => {
        console.log(data);
        this.refresh();
      },
      response => {
        console.log(response);
      }
    );
  }

  reminderAdd(noteId, str) {
    const d = new Date();
    this.reminderFrequency = null;
    if(str=='today'){
      this.reminderDate = d.getMonth()+1+'/'+d.getDate()+'/'+d.getFullYear();
      this.reminderTime = '8:00 PM';
      this.addReminder(noteId);
      return;
    }
    if(str=='tomorrow'){
      d.setDate(d.getDate()+1);
      this.reminderDate = d.getMonth()+1+'/'+d.getDate()+'/'+d.getFullYear();
      this.reminderTime = '8:00 AM';
      this.addReminder(noteId);
      return;
    }
    if(str=='next'){
      d.setDate(d.getDate() + (1 + 7 - d.getDay()) % 7);
      this.reminderDate = d.getMonth()+1+'/'+d.getDate()+'/'+d.getFullYear();
      this.reminderTime = '8:00 AM';
      this.addReminder(noteId);
      return;
    }
  }

}
