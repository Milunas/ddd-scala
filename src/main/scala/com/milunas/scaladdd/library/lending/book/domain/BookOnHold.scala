package com.milunas.scaladdd.library.lending.book.domain

import java.time.Instant

import com.milunas.scaladdd.library.lending.patron.domain.Patron

case class BookOnHold(private val by: Patron,
                 private val book: AvailableBook,
                 private val when: Instant) extends Book
