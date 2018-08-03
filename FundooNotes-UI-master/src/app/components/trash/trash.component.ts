import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpParams } from '@angular/common/http';
import { HttpService } from '../../services/http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trash',
  templateUrl: './trash.component.html',
  styleUrls: ['./trash.component.css']
})
export class TrashComponent implements OnInit {

  constructor(private httpService: HttpService,
    private router: Router) { }

  records: any = [];
  token = "";

  ngOnInit() {
    this.token = localStorage.getItem('fundoo_token');
    console.log(this.token);

    const options = { headers: new HttpHeaders().append('Authorization', this.token) };
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

  delete(noteId) {
    const options = { headers: new HttpHeaders().append('Authorization', this.token) };    
    this.httpService.delete("http://localhost:8080/note/deletenote/" + noteId, options)
      .subscribe(
        (data) => {
          console.log(data);
          this.ngOnInit();
        },
        response => {
          console.log(response.error);
        }
      );
  }

  restore(noteId){
    const options = { headers: new HttpHeaders()
      .append('Authorization', this.token)
      .append('Content-Type', 'application/x-www-form-urlencoded') };
    const body = new HttpParams()
      .set('noteId', noteId);
    this.httpService.put("http://localhost:8080/note/restorenote/", body, options)
      .subscribe(
        (data) => {
          console.log(data);
          this.ngOnInit();
        },
        response => {
          console.log(response.error);
        }
      );
  }

}

