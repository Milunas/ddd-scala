package com.milunas.scaladdd.library.lending.patron.application

import cats.effect.IO
import com.milunas.scaladdd.library.lending.book.infrastructure.BookRepository
import com.milunas.scaladdd.library.lending.patron.infrastructure.PatronRepository

class PlacingOnHold(private val patrons: PatronRepository,
                    private val books: BookRepository) {

  def placeOnHold(patronId: Int, bookId: Int): IO[Unit] =
    for {
      patron <- patrons.findBy(patronId)
      book <- books.findBy(bookId)
      hold <- patron.placeOnHold(book).fold(IO.raiseError, IO.pure)
      _ <- books.save(hold)
    } yield ()
}
