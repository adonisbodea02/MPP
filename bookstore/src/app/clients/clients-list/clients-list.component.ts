import { Component, OnInit } from '@angular/core';
import {Client} from "../common/client.model";
import {ClientsService} from "../common/clients.service";

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {

  clients: Client[];

  constructor(private clientsService: ClientsService) {
    this.loadClients();
  }

  ngOnInit() {
  }

  loadClients(){
    this.clientsService.getClients().subscribe(received=> {this.clients = received;
      console.log(this.clients);});
  }

}
