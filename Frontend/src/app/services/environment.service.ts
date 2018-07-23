import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  static APIpath = 'http://localhost:9001/OurSpace/';
<<<<<<< HEAD
  // static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/';
=======
  // static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/Our-Space-V2/';
>>>>>>> develop
  constructor() { }
}
