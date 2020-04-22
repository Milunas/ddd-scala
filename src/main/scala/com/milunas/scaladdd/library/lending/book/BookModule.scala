package com.milunas.scaladdd.library.lending.book

import cats.effect.IO
import com.milunas.scaladdd.library.lending.book.infrastructure.BookRepository

class BookModule() {
  lazy val bookRepository = new BookRepository()
}

object BookModule {
  def createSync(): IO[BookModule] = IO.delay(new BookModule())
}
