import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

<<<<<<< HEAD
  static APIpath = 'http://localhost:9005/OurSpace/';
  // static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/Our-Space-V2/';
=======
  // static APIpath = 'http://localhost:9001/OurSpace/';
  static APIpath = 'http://ec2-54-145-139-170.compute-1.amazonaws.com:8080/';
>>>>>>> 286a9e9e48cd28c04064ebf0a5c21390b19ca76c
  constructor() { }
}
