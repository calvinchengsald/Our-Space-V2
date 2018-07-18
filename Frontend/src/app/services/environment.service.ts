import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  static APIpath = 'http://localhost:9001/OurSpace/';
  constructor() { }
}
