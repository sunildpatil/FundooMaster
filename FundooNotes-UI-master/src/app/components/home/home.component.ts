import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  constructor(private router: Router) {
  }
  
  ngOnInit() {    
    let token = localStorage.getItem('fundoo_token');
    if (token == "" || token == null)
      this.router.navigate(['login']);
  }

  archiveTab(){
    this.router.navigate(['home/archive']);
  }

  trashTab(){
    this.router.navigate(['home/trash']);
  }

  logout(){
    localStorage.removeItem("fundoo_token");
    this.router.navigate(['login']);
  }

}
