package com.milunas.scaladdd.library.lending.book.infrastructure

import cats.effect.IO
import com.milunas.scaladdd.library.lending.book.domain.Book
import com.milunas.scaladdd.library.lending.book.infrastructure.exception.BookNotFound
import doobie.implicits._
import doobie._

class BookRepository() {

  def findBy(id: Long): ConnectionIO[Book] = BookSQL.select(id, "AVAILABLE")
    .option
    .map(maybe => maybe.toRight(new BookNotFound))
    .flatMap(maybe => maybe.fold(IO.raiseError, IO.pure).to[ConnectionIO])

  def save(book: Either[Throwable, Book]): ConnectionIO[Int] =
    book match { case Right(book) => BookSQL.update(book, book.id).run }
}
