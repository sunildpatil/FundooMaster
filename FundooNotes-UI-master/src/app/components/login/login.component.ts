import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../services/http.service'
import { NgForm } from '@angular/forms';
// import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private httpService: HttpService,
    private router: Router) { 

  }

  ngOnInit() {
  }

  passwordError = "";
  emailError = "";
  name = "";

  login(form: NgForm){
    if(form.value.email==""){
      this.emailError = "Field cannot be blank.";
      return;
    }
    if(form.value.password==""){
      this.passwordError = "Field cannot be blank.";
      return;
    }
    this.login1(form);
  }

  login1(form) {
    this.httpService.post(environment.login_url, form.value, this.httpService.optionsJson)
      .subscribe(
        (data) => {
          console.log("POST call successful value returned in body", data);
          localStorage.setItem('fundoo_token', JSON.parse(JSON.stringify(data)).message);
          this.router.navigate(['home']);
        },
        response => {
          console.log("POST call in error", response.error);
          if (response.error.message == 'Incorrect Password')
            this.passwordError = environment.wrong_password;
          if (response.error.message == 'Invalid Email Id')
            this.emailError = environment.wrong_email;
          if (response.error.message == 'Please Activate Account First')
            this.emailError = environment.account_not_activated;
        }
      );
  }

}