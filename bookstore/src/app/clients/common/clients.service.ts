import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from "./client.model";

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  private serverURL: string = 'http://localhost:8080/api/clients';

  constructor(private httpClient: HttpClient) { }

  getClients(): Observable<Client[]>{
    return this.httpClient.get<Client[]>(this.serverURL);
  }
}
