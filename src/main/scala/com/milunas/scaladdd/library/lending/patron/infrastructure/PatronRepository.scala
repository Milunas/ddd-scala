package com.milunas.scaladdd.library.lending.patron.infrastructure

import cats.effect.IO
import com.milunas.scaladdd.library.lending.patron.domain.Patron
import com.milunas.scaladdd.library.lending.patron.infrastructure.exception.PatronNotFound
import doobie.implicits._
import cats.implicits._
import doobie.util.transactor.Transactor

class PatronRepository(private val transactor:Transactor[IO]) {

  def findBy(patronId: Long): IO[Patron] = PatronSQL.select(patronId)
    .option
    .transact(transactor)
    .map(maybe => maybe.toRight(new PatronNotFound()))
    .flatMap(maybe => maybe.fold(IO.raiseError, IO.pure))
}
