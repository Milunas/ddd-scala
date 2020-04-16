package com.milunas.scaladdd.library.lending.book.domain

import java.time.Instant

import com.milunas.scaladdd.library.lending.book.domain.BookStatus.Status

case class Book(id: Long,
                private var status: Status,
                private var onHoldByPatron: Long,
                private var onHoldTill: Instant)

object Book {
  def canBePlaced(book: Book): Boolean = book.status == BookStatus.AVAILABLE

  def place(patronId: Long, book: Book) = book.status = BookStatus.ON_HOLD
}
