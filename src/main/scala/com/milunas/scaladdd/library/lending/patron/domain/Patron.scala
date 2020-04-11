package com.milunas.scaladdd.library.lending.patron.domain

import java.time.Instant
import com.milunas.scaladdd.library.lending.book.domain.{AvailableBook, BookOnHold}

final case class Patron(private val overdueCheckouts: Int,
                        private var heldBooks: List[BookOnHold]) {

  def placeOnHold(book: AvailableBook): Either[Throwable, BookOnHold] =
    if(heldBooks.size < 5 && overdueCheckouts < 2) {
      val hold = BookOnHold(this, book, Instant.now)
      heldBooks = hold :: heldBooks
      Right(hold)
    }
    else Left(new RuntimeException())
}
