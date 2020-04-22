package com.milunas.scaladdd.library.lending.patron.domain

import com.milunas.scaladdd.library.lending.book.domain.Book
import com.milunas.scaladdd.library.lending.patron.domain.exception.CannotPlaceBookOnHold

final case class Patron(id: Long, overdueCheckouts: Int, var heldBooks: List[Book]) {

  def placeOnHold(book: Book): Either[Throwable, Book] =
    if(true /*canBePlaced(book)*/) {
      //book.place(id, book)
      heldBooks = book :: heldBooks
      Right(book)
    }
    else Left(new CannotPlaceBookOnHold("Cannot place book"))

  //private def canBePlaced(book: Book) = book.canBePlaced(book) && heldBooks.size < 5 && overdueCheckouts < 2

}
