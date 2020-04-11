package com.milunas.scaladdd.library.lending.patron.web

import cats.effect.IO
import com.milunas.scaladdd.library.lending.patron.application.PlacingOnHold
import org.http4s.dsl.Http4sDsl
import org.http4s.{HttpRoutes, Response}

class PatronRouter(private val placingOnHold: PlacingOnHold) extends Http4sDsl[IO] {

  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
     case PUT -> Root / "patrons" / patronId / "books" / bookId / "holds" =>
      placingOnHold.placeOnHold(patronId.toInt, bookId.toInt) match {
        case Left(error) => mapError(error)
        case Right(_) => NoContent()
      }
  }

  def mapError(error: Throwable): IO[Response[IO]] = NotFound()
}
