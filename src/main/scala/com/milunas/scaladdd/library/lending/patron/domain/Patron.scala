package com.milunas.scaladdd.library.lending.patron.domain

import com.milunas.scaladdd.library.lending.book.domain.{AvailableBook, BookOnHold}
import com.milunas.scaladdd.library.lending.patron.domain.event.BookPlacedOnHold

final case class Patron(private val overdueCheckouts: Int,
                        private var holdBooks: List[BookOnHold]) {

  def placeOnHold(book: AvailableBook): Either[Throwable, BookPlacedOnHold] =
    if(canBeHold) Right(new BookPlacedOnHold())
    else Left(new RuntimeException())


  private def canBeHold: Boolean = ???
}
