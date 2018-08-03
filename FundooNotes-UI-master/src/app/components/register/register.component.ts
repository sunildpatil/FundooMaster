import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpService } from '../../services/http.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }

  emailError = "";
  passError = "";

  show: boolean = false;

  password() {
    this.show = !this.show;
  }

  register(form: NgForm) {
    if(form.value.firstName=='' || form.value.lastName=='' ||
      form.value.email=='' || form.value.contactNumber=='' ||
      form.value.password=='' || form.value.confirmPassword==''){
        this.passError = "One or more field/s is left blank";
        return;
    }
    if (form.value.password == form.value.confirmPassword)
      this.register1(form);
    else
      this.passError = "Passwords didnt match.";
  }

  register1(form) {
    this.httpService.post(environment.registration_link, form.value, this.httpService.optionsJson).subscribe(
      (val) => {
        console.log("POST call successful value returned in body", val);
      },
      response => {
        console.log("POST call in error", response.error);
        if (response.error.message == 'Email already registered')
          this.emailError = environment.email_already_registered;
      });
  }

}
