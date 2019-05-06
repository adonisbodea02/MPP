import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BooksRoutingModule } from './books-routing.module';
import {BooksListComponent} from "./books-list/books-list.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {BooksService} from "./common/books.service";

@NgModule({
  declarations: [
    BooksListComponent
  ],
  imports: [
    CommonModule,
    BooksRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    BooksService
  ]

})
export class BooksModule { }
