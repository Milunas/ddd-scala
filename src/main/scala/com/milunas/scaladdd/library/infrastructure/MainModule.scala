package com.milunas.scaladdd.library.infrastructure

import cats.effect.{ConcurrentEffect, IO, Timer}
import com.milunas.scaladdd.library.lending.book.BookModule
import com.milunas.scaladdd.library.lending.patron.PatronModule
import org.http4s.server.blaze.BlazeServerBuilder

class MainModule {

  def start(implicit concurrentEffect: ConcurrentEffect[IO], timer: Timer[IO]): IO[Unit] = program

  def program(implicit concurrentEffect: ConcurrentEffect[IO], timer: Timer[IO]): IO[Unit] = {
    for {
      bookModule <- BookModule.createSync()
      patronModule <- PatronModule.createSync(bookModule.bookRepository)
      api = new HttpConfig(patronModule.routes)
      _ <- BlazeServerBuilder[IO].bindHttp(8080, "0.0.0.0").withHttpApp(api.httpApp).serve.compile.drain
    } yield ()
  }
}
