package com.milunas.scaladdd.library.lending.book.infrastructure

import com.milunas.scaladdd.library.lending.book.domain.{AvailableBook, Book}

class BookRepository extends FindAvailableBook {

  def save(book: Book) = ???

  override def by(bookId: Int): Either[Throwable, AvailableBook] = ???
}
