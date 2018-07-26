import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { EnvironmentService } from './../../services/environment.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent implements OnInit {
  private activationKey: string;
  message: string;

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.activationKey = params['activationKey'];
      console.log(this.activationKey);
      if (this.activationKey) {
        this.activate();
      }
    });
  }

  activate() {
    const url: string = EnvironmentService.APIpath + 'activation.action';
    const httpOptions = {
      headers: new HttpHeaders({
      }),
      withCredentials: true
    };
    console.log(url);
    this.http.post(url, {activationKey: this.activationKey}, httpOptions).subscribe(
      data => {
        if (data) {
          this.message = data['email'] + ' successfully activated';
        } else {
          this.message = 'Activation failed';
        }
      }
    );
  }

}
