import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from '../../services/http.service';
import { HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private httpService: HttpService,
    private router: Router) { }

  token: string = "";
  password: string = "";
  confirmPassword: string = "";
  matchError = "";

  ngOnInit() {

  }

  confirmPass() {
    if (this.password == "" || this.confirmPassword == "") {
      this.matchError = environment.empty_field;
      return;
    }
    if (this.password === this.confirmPassword)
      this.changePass();
    else
      this.matchError = environment.matchError;
  }

  changePass() {
    this.token = this.route.snapshot.paramMap.get('token');
    const body = new HttpParams()
      .set('password', this.password);
    this.httpService.post(environment.new_password_url + this.token, body, this.httpService.optionsUrl).subscribe(
      (val) => {
        console.log("POST call successful value returned in body", val);
        this.router.navigate(['login']);
      },
      response => {
        console.log("POST call in error", response.error);
        if (response.error.message == 'Token Expired') {
          this.matchError = environment.link_expired;
          return;
        }
        this.matchError = environment.password_change_problem;
      });
  }

}
