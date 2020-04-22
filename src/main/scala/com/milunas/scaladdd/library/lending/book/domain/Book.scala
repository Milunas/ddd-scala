package com.milunas.scaladdd.library.lending.book.domain

case class Book(id: Long, var status: String, onHoldByPatron: Long, onHoldTill: String) {
  def canBePlaced(book: Book): Boolean = book.status == "Available"

  def place(patronId: Long, book: Book) = book.status = "On_Hold"
}
