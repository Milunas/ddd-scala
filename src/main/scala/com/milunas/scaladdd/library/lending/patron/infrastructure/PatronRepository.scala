package com.milunas.scaladdd.library.lending.patron.infrastructure

import com.milunas.scaladdd.library.lending.patron.domain.Patron

class PatronRepository {

  def save(patron: Patron): Either[RuntimeException, Patron] = ???

  def findById(patronId: Int): Either[Throwable, Patron] = ???
}
