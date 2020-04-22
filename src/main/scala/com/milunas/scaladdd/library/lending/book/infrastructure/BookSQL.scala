package com.milunas.scaladdd.library.lending.book.infrastructure

import com.milunas.scaladdd.library.lending.book.domain.Book
import doobie._
import doobie.implicits._

object BookSQL {

  def select(id: Long, status: String) =
    sql"""
         SELECT id, status, onHoldByPatron, onHoldTill
         FROM book
         WHERE id = $id
      """.query[Book]

  def update(book: Book, id: Long): Update0 = sql"""
      UPDATE book
      SET
          id = $book.id,
          status = $book.status,
          onHoldByPatron = $book.onHoldByPatron,
          till = $book.onHoldTill
      WHERE id = $id
    """.stripMargin.update
}
