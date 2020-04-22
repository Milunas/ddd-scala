package com.milunas.scaladdd.library.lending.patron.infrastructure

import cats.effect.IO
import com.milunas.scaladdd.library.lending.patron.domain.Patron
import com.milunas.scaladdd.library.lending.patron.infrastructure.exception.PatronNotFound
import doobie.free.connection.ConnectionIO

class PatronRepository() {

  def findBy(patronId: Long): ConnectionIO[Patron] = PatronSQL.select(patronId)
    .option
    .map(maybe => maybe.toRight(new PatronNotFound()))
    .flatMap(maybe => maybe.fold(IO.raiseError, IO.pure).to[ConnectionIO])
}
