import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessegeModelService {


  // _show: boolean;
  show: boolean;
  messege: string;
  error: boolean;
  constructor() { }

}
