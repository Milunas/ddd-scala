package com.milunas.scaladdd.library.lending.book.infrastructure

import com.milunas.scaladdd.library.lending.book.domain.{AvailableBook, Book}

class BookRepository {
  def findBy(bookId: Int): Either[Throwable, AvailableBook] = ???

  def save(book: Book): Either[Throwable, Unit] = ???

}
