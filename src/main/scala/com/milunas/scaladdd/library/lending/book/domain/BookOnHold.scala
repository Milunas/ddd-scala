package com.milunas.scaladdd.library.lending.book.domain

import java.time.Instant

class BookOnHold(private val when: Instant) extends Book {

}
