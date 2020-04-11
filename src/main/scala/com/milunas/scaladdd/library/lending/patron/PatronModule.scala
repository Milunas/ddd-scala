package com.milunas.scaladdd.library.lending.patron

import cats.effect.IO
import com.milunas.scaladdd.library.commons.events.DomainEventPublisher
import com.milunas.scaladdd.library.lending.book.infrastructure.FindAvailableBook
import com.milunas.scaladdd.library.lending.patron.application.PlacingOnHold
import com.milunas.scaladdd.library.lending.patron.infrastructure.PatronRepository
import com.milunas.scaladdd.library.lending.patron.web.PatronRouter


class PatronModule(findAvailableBook: FindAvailableBook,
                   publisher: DomainEventPublisher) {
  lazy val patrons = new PatronRepository()
  lazy val placingOnHold = new PlacingOnHold(patrons, findAvailableBook, publisher)
  lazy val routes = new PatronRouter(placingOnHold)
}

object PatronModule {
  def createSync(findAvailableBook: FindAvailableBook, publisher: DomainEventPublisher):
    IO[PatronModule] = IO.delay(new PatronModule(findAvailableBook, publisher))
}
