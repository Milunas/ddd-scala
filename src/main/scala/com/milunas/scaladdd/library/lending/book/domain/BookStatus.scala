package com.milunas.scaladdd.library.lending.book.domain

object BookStatus {
  sealed trait Status
  case object AVAILABLE extends Status
  case object ON_HOLD extends Status
}
