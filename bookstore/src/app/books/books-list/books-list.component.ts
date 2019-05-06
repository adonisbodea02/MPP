import { Component, OnInit } from '@angular/core';
import {Book} from "../common/book.model";
import {BooksService} from "../common/books.service";

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  books: Book[];

  constructor(private booksService: BooksService) {
    this.loadBooks();
  }

  ngOnInit() {
  }

  loadBooks(){
    this.booksService.getBooks().subscribe(received=> {this.books = received;
      console.log(this.books);});
  }

}
