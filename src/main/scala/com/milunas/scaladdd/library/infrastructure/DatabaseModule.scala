package com.milunas.scaladdd.library.infrastructure

import cats.effect.{Blocker, IO, Resource}
import com.milunas.scaladdd.library.infrastructure.config.Config.DatabaseConfig
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts

object DatabaseModule {
  implicit val cs = IO.contextShift(ExecutionContexts.synchronous)

  def createTransactor(databaseConfig: DatabaseConfig): Resource[IO, HikariTransactor[IO]] = for {
    ce <- ExecutionContexts.fixedThreadPool[IO](32) // our connect EC
    be <- Blocker[IO]    // our blocking EC
    xa <- HikariTransactor.newHikariTransactor[IO](
      "org.h2.Driver",               // driver classname
      "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",    // connect URL
      "sa",                                   // username
      "",                                     // password
      ce,                                     // await connection here
      be                                      // execute JDBC operations here
    )
  } yield xa
}

