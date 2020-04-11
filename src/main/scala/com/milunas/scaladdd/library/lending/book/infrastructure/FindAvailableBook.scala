package com.milunas.scaladdd.library.lending.book.infrastructure

import com.milunas.scaladdd.library.lending.book.domain.AvailableBook

@FunctionalInterface
trait FindAvailableBook {
  def by(bookId: Int): Either[Throwable, AvailableBook]
}
