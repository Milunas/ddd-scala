package com.milunas.scaladdd.library.lending.patron.application

import cats.effect.IO
import com.milunas.scaladdd.library.lending.book.infrastructure.BookRepository
import com.milunas.scaladdd.library.lending.patron.infrastructure.PatronRepository
import doobie.free.connection.ConnectionIO
import doobie.implicits._
import doobie.util.transactor.Transactor

class PlacingOnHold(private val patrons: PatronRepository,
                    private val books: BookRepository,
                    private val transactor: Transactor[IO]) {

  def placeOnHold(patronId: Int, bookId: Int): IO[Unit] =
    place(patronId, bookId).transact(transactor)

  private def place(patronId: Int, bookId: Int): ConnectionIO[Unit] =
    for {
      patron <- patrons.findBy(patronId)
      book <- books.findBy(bookId)
      hold = patron.placeOnHold(book)
      update <- books.save(hold)
    } yield update
}
