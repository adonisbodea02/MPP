import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientsRoutingModule } from './clients-routing.module';
import {ClientsListComponent} from "./clients-list/clients-list.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {ClientsService} from "./common/clients.service";

@NgModule({
  declarations: [
    ClientsListComponent
  ],
  imports: [
    CommonModule,
    ClientsRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    ClientsService
  ]
})
export class ClientsModule { }
