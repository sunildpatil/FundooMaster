import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  
  constructor(private http: HttpClient) { }

  post(url, data, options) {
    return this.http.post(url, data, options)      
  }

  get(url, options) {
    return this.http.get(url, options);
  }

  put(url, data, options) {
    return this.http.put(url, data, options);
  }

  delete(url, options) {
    return this.http.delete(url, options);
  }  
  
  public optionsUrl = { headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded') };  
  public optionsJson = { headers: new HttpHeaders().set('Content-Type', 'application/json')};
}
