package com.milunas.scaladdd.library.lending.patron

import cats.effect.IO
import com.milunas.scaladdd.library.lending.book.infrastructure.BookRepository
import com.milunas.scaladdd.library.lending.patron.application.PlacingOnHold
import com.milunas.scaladdd.library.lending.patron.infrastructure.PatronRepository
import com.milunas.scaladdd.library.lending.patron.web.PatronRouter
import doobie.util.transactor.Transactor

class PatronModule(transactor: Transactor[IO], bookRepository: BookRepository) {
  lazy val patrons = new PatronRepository(transactor)
  lazy val placingOnHold = new PlacingOnHold(patrons, bookRepository)
  lazy val routes = new PatronRouter(placingOnHold)
}

object PatronModule {
  def createSync(transactor: Transactor[IO], bookRepository: BookRepository):
    IO[PatronModule] = IO.delay(new PatronModule(transactor, bookRepository))
}
