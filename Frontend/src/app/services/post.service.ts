import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private _url = 'https://pokeapi.co/api/v2/pokemon/7/';

  constructor(private httpServ: HttpClient) { }

  getAllPost(): Observable<string> {
    return this.httpServ.get(this._url).pipe(
      map(res => res as string)
    );
  }

  getOurPost(): Observable<string> {
    return this.httpServ.get(this._url).pipe(
      map(res => res as string)
    );
  }
}
