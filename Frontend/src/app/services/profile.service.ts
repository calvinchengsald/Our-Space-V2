import { Injectable } from '@angular/core';
import { EnvironmentService } from './environment.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  url: string;
  httpOptions = {
    headers: new HttpHeaders({
    })
  };

  constructor(private httpServ: HttpClient) { }

  getProfile(first_namez: string, last_namez: string, emailz: string): Observable<string> {

    console.log('in getprofile method with params ' + emailz + '/' + first_namez + '/' + last_namez);
    this.url = EnvironmentService.APIpath + 'profile.action';
    const obj = {
      email: emailz,
      firstName: first_namez,
      lastName: last_namez
    };
    return this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }
}
