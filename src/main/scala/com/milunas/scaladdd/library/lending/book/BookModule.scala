package com.milunas.scaladdd.library.lending.book

import cats.effect.IO
import com.milunas.scaladdd.library.lending.book.infrastructure.BookRepository
import doobie.util.transactor.Transactor

class BookModule(transactor: Transactor[IO]) {
  lazy val bookRepository = new BookRepository(transactor)
}

object BookModule {
  def createSync(transactor: Transactor[IO]): IO[BookModule] = IO.delay(new BookModule(transactor))
}
