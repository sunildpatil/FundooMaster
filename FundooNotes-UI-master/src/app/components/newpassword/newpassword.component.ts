import { Component, OnInit } from '@angular/core';
import { HttpParams, HttpHeaders } from '@angular/common/http';
import { HttpService } from '../../services/http.service';

@Component({
  selector: 'app-newpassword',
  templateUrl: './newpassword.component.html',
  styleUrls: ['./newpassword.component.css']
})
export class NewpasswordComponent implements OnInit {

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }

  public email = "";
  emailError = "";

  forgotpassword() {
    const body = new HttpParams()
      .set('email', this.email);
    const options = { headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded') };

    this.httpService.post("http://localhost:8080/user/forgotpassword", body, options).subscribe(
      (val) => {
        console.log("POST call successful value returned in body", val);
      },
      response => {
        console.log("POST call in error", response.error);
        if(response.error.message == 'Incorrect email id')
        this.emailError = "Incorrect email id";
        
      });
  }

}
