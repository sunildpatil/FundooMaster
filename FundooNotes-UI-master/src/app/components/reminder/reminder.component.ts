import { Component, OnInit } from '@angular/core';
import { MatDatepickerInputEvent, MatDialog } from '@angular/material';
import { HttpService } from '../../services/http.service';
import { Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-reminder',
  templateUrl: './reminder.component.html',
  styleUrls: ['./reminder.component.css']
})
export class ReminderComponent implements OnInit {

  records: any = [];
  token = "";
  archiveIcon = 'archive';
  
  constructor(private httpService: HttpService,
    private router: Router) { }

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
}
