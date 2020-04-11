package com.milunas.scaladdd.library.infrastructure

import cats.effect.{ExitCode, IO, IOApp}
import cats.syntax.all._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = (new MainModule).start.as(ExitCode.Success)
}
