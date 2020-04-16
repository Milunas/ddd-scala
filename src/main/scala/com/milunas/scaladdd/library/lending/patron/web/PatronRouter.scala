package com.milunas.scaladdd.library.lending.patron.web

import cats.effect.IO
import com.milunas.scaladdd.library.lending.patron.application.PlacingOnHold
import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes

class PatronRouter(private val placingOnHold: PlacingOnHold) extends Http4sDsl[IO] {

  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case PUT -> Root / "patrons" / patronId / "books" / bookId / "holds" =>
      placingOnHold.placeOnHold(patronId.toInt, bookId.toInt)
        .flatMap(_ => NoContent())
        .handleErrorWith(mapError)
  }

  def mapError(error: Throwable)  = NotFound(error.getMessage)
}
