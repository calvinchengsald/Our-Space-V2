import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessegeModelService {


  // _show: boolean;
  _show: boolean;
  _messege: string;
  _error: boolean;
  constructor() { }

  set messege(mes: string) {
    this._messege = mes;
  }
  get messege(): string {
    return this._messege;
  }

  set show(mes: boolean) {
    this._show = mes;
  }
  get show(): boolean {
    return this._show;
  }

  set error(err: boolean) {
    this._error = err;
  }
  get error(): boolean {
    return this._error;
  }

}
