package com.milunas.scaladdd.library.lending.patron.infrastructure

import com.milunas.scaladdd.library.lending.patron.domain.Patron
import doobie.Query0
import doobie.implicits._

object PatronSQL {
  def select(id: Long): Query0[Patron] = sql"""
    SELECT p.*, b.*
    FROM patrons AS p
    JOIN books AS b
    ON p.$id = b.on_hold_by_patron
    WHERE p.id = $id
  """.query[Patron]
}
