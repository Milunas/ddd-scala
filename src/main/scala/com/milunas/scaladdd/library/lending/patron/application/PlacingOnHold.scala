package com.milunas.scaladdd.library.lending.patron.application

import com.milunas.scaladdd.library.lending.book.infrastructure.BookRepository
import com.milunas.scaladdd.library.lending.patron.infrastructure.PatronRepository

class PlacingOnHold(private val patrons: PatronRepository,
                    private val books: BookRepository) {

  def placeOnHold(patronId: Int, bookId: Int): Either[Throwable, Unit] =
    for {
      patron <- patrons.findById(patronId)
      book <- books.findBy(bookId)
      hold <- patron.placeOnHold(book)
      _ <- books.save(hold)
      _ <- patrons.save(patron)
    } yield ()
}
