package com.milunas.scaladdd.library.lending.book.infrastructure

import cats.effect.IO
import com.milunas.scaladdd.library.lending.book.domain.Book
import com.milunas.scaladdd.library.lending.book.infrastructure.exception.BookNotFound
import doobie.util.transactor.Transactor
import doobie.implicits._
import doobie._

class BookRepository(transactor: Transactor[IO]) {

  def findBy(id: Int) = select(id, "AVAILABLE")
    .option
    .transact(transactor)
    .map(maybe => maybe.toRight(new BookNotFound()))
    .flatMap(maybe => maybe.fold(IO.raiseError, IO.pure))

  def save(book: Book) = update(book, book.id).run.transact(transactor).map(_ => Unit)

  def select(id: Long, status: String) = sql"""
    SELECT status, onHoldByPatron, onHoldTill
    FROM book
    WHERE id = $id
  """.query[Book]

  def update(book: Book, id: Long): Update0 =sql"""
      UPDATE book
      SET status = $book.status,
          onHoldByPatron = $book.onHoldByPatron,
          till = $book.onHoldTill
      WHERE id = $id
    """.stripMargin.update
}
