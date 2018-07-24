import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {


<<<<<<< HEAD
  // static APIpath = 'http://localhost:9005/OurSpace/';
=======
  // static APIpath = 'http://localhost:9001/OurSpace/';
>>>>>>> develop
   static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/';

  constructor() { }
}
