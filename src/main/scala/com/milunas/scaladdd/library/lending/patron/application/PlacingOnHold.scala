package com.milunas.scaladdd.library.lending.patron.application

import com.milunas.scaladdd.library.commons.events.DomainEventPublisher
import com.milunas.scaladdd.library.lending.book.infrastructure.FindAvailableBook
import com.milunas.scaladdd.library.lending.patron.infrastructure.PatronRepository

class PlacingOnHold(private val patrons: PatronRepository,
                    private val findAvailableBook: FindAvailableBook,
                    private val events: DomainEventPublisher) {

  def placeOnHold(patronId: Int, bookId: Int): Either[Throwable, Unit] =
    for {
      patron <- patrons.findById(patronId)
      book <- findAvailableBook.by(bookId)
      hold <- patron.placeOnHold(book)
      _ <- events.publish(hold)
      _ <- patrons.save(patron)
    } yield ()
}
