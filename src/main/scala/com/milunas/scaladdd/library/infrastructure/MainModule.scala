package com.milunas.scaladdd.library.infrastructure

import cats.effect.{ConcurrentEffect, IO, Timer}
import com.milunas.scaladdd.library.infrastructure.config.{ApiConfig, Config}
import com.milunas.scaladdd.library.infrastructure.config.Config.AppConfig
import com.milunas.scaladdd.library.lending.book.BookModule
import com.milunas.scaladdd.library.lending.patron.PatronModule
import doobie.util.transactor.Transactor
import org.http4s.server.blaze.BlazeServerBuilder

class MainModule {

  def start(implicit concurrentEffect: ConcurrentEffect[IO], timer: Timer[IO]): IO[Unit] = {
    for {
      config <- Config.loadSync
      transactorResource = DatabaseModule.createTransactor(config.database)
      _ <- transactorResource.use(transactor => program(config, transactor))
    } yield ()
  }

  def program(app: AppConfig, transactor: Transactor[IO])(implicit concurrentEffect: ConcurrentEffect[IO], timer: Timer[IO]): IO[Unit] = {
    for {
      bookModule <- BookModule.createSync(transactor)
      patronModule <- PatronModule.createSync(transactor, bookModule.bookRepository)
      api = new ApiConfig(patronModule.routes)
      _ <- BlazeServerBuilder[IO].bindHttp(app.http.port, app.http.host)
        .withHttpApp(api.httpApp)
        .serve
        .compile
        .drain
    } yield ()
  }
}
