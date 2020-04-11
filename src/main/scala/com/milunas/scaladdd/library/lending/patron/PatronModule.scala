package com.milunas.scaladdd.library.lending.patron

import cats.effect.IO
import com.milunas.scaladdd.library.lending.book.infrastructure.BookRepository
import com.milunas.scaladdd.library.lending.patron.application.PlacingOnHold
import com.milunas.scaladdd.library.lending.patron.infrastructure.PatronRepository
import com.milunas.scaladdd.library.lending.patron.web.PatronRouter

class PatronModule(bookRepository: BookRepository) {
  lazy val patrons = new PatronRepository()
  lazy val placingOnHold = new PlacingOnHold(patrons, bookRepository)
  lazy val routes = new PatronRouter(placingOnHold)
}

object PatronModule {
  def createSync(bookRepository: BookRepository):
    IO[PatronModule] = IO.delay(new PatronModule(bookRepository))
}
