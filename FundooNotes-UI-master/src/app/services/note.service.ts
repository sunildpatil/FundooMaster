import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { HttpHeaders, HttpParams } from '@angular/common/http';
import { MatDialogConfig, MatDialog } from '@angular/material';
import { DialogComponent } from '../components/dialog/dialog.component';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private httpService: HttpService,
    private dialog: MatDialog) { }

  token: string = localStorage.getItem("fundoo_token");

  public optionsUrl = {
    headers: new HttpHeaders()
      .set('Authorization', this.token)
      .set('Content-Type', 'application/x-www-form-urlencoded')
  };

  public optionsJson = {
    headers: new HttpHeaders()
      .set('Authorization', this.token)
      .set('Content-Type', 'application/json')
  }

  create(form) {
    return this.httpService.post("http://localhost:8080/note/createnote", form.value, this.optionsJson)
  }

  delete(noteId){
    return this.httpService.delete("http://localhost:8080/note/deletenote/" + noteId, this.optionsUrl)
  }

  update(note){
    return this.httpService.put("http://localhost:8080/note/updatenote", note, this.optionsJson)
  }

  archiveNote(noteId){
    const body = new HttpParams()
      .set('noteId', noteId);
    return this.httpService.put("http://localhost:8080/note/archivenote", body, this.optionsUrl)
  }

  changeColor(color, noteId){
    const body = new HttpParams()
      .set('noteId', noteId)
      .set('color', color);
    return this.httpService.put("http://localhost:8080/note/colornote", body, this.optionsUrl)
  }

  addReminder(noteId, reminderDate, reminderTime, reminderFrequency){
    const remind = {
      'noteId': noteId,
      'reminderDate': reminderDate,
      'reminderTime': reminderTime,
      'reminderFequency': reminderFrequency
    };
    return this.httpService.put("http://localhost:8080/note/addreminder", remind, this.optionsJson)
  }

  deleteReminder(noteId) {
    const body = new HttpParams()
      .set('noteId', noteId);
    return this.httpService.put("http://localhost:8080/note/deletereminder", body, this.optionsUrl)      
  }

  openDialog(data){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = data;
    return this.dialog.open(DialogComponent, dialogConfig).afterClosed()
  }

}
