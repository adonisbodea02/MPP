import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Book} from "./book.model";

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  private serverURL: string = 'http://localhost:8080/api/books';

  constructor(private httpClient: HttpClient) { }

  getBooks(): Observable<Book[]>{
    return this.httpClient.get<Book[]>(this.serverURL);
  }
}
