import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

<<<<<<< HEAD
  // static APIpath = 'http://localhost:9005/OurSpace/';
  static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/';
=======


<<<<<<< HEAD
  // static APIpath = 'http://localhost:9005/OurSpace/';
   static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/';
=======
  static APIpath = 'http://localhost:9001/OurSpace/';
  //  static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/';
>>>>>>> e48b2340a3932a4207b5fb107b3cd67d997f74be
>>>>>>> develop


  constructor() { }
}
