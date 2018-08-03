import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../services/http.service';
import { HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-archive',
  templateUrl: './archive.component.html',
  styleUrls: ['./archive.component.css']
})
export class ArchiveComponent implements OnInit {

  constructor(private httpService: HttpService,
    private router: Router) { }

  records: any = [];
  token = "";
  archiveIcon = 'unarchive';

  ngOnInit() {
    this.token = localStorage.getItem('fundoo_token');
    this.refresh();
  }

  refresh(){
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
}
