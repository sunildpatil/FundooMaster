import { Component, OnInit, Injectable, ViewChild, AfterViewInit } from '@angular/core';
import { HttpHeaders, HttpParams } from '@angular/common/http';
import { HttpService } from '../../services/http.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { NoteService } from '../../services/note.service';
import { TemplateComponent } from '../template/template.component';


@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})

@Injectable()
export class NoteComponent implements OnInit {

  constructor(private httpService: HttpService,
    private noteService: NoteService,
    private router: Router) { }

  records: any = [];
  token = "";
  showCard = false;
  title = "";
  description = "";
  archiveIcon = 'archive';

  ngOnInit() {
    this.token = localStorage.getItem('fundoo_token');
    console.log(this.token);    
    this.refresh();
  }

  refresh(){
    const options = {
      headers: new HttpHeaders().append('Authorization', this.token)
    };
    this.httpService.get("http://localhost:8080/note/displaynotes", options)
      .subscribe(
        (data) => {
          this.records = data;
        },
        response => {
          console.log(response.error);
          this.router.navigate(['login']);
        }
      );
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

  toggleShowCard(form: NgForm) {
    this.showCard = !this.showCard;
    if (this.title == "" && this.description == "") {
      //skip
    }
    else
      this.createNote(form);
  }
}
