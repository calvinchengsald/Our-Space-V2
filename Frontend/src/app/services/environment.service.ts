import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

<<<<<<< HEAD
<<<<<<< HEAD
   static APIpath = 'http://localhost:9005/OurSpace/';
  // static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/Our-Space-V2/';
=======
<<<<<<< HEAD

  static APIpath = 'http://localhost:9005/OurSpace/';
  //static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/';
=======
  // static APIpath = 'http://localhost:9001/OurSpace/';
  static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/';
>>>>>>> develop
>>>>>>> 785ac51ef16e2a1b473faad131dfe5327f148e0d
=======
   static APIpath = 'http://localhost:9001/OurSpace/';
  // static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/';
>>>>>>> 3312ea8bbcbacd4f4c42c8825574f78b684bcb49
  constructor() { }
}
