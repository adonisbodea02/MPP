import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BooksListComponent} from "./books-list/books-list.component";

const routes: Routes = [
  {
    path: '',
    component: BooksListComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BooksRoutingModule { }
