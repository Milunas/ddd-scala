package com.milunas.scaladdd.library.infrastructure

import cats.effect.IO
import com.milunas.scaladdd.library.lending.patron.web.PatronRouter
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.{HttpApp, HttpRoutes}

class HttpConfig(patronRoutes: PatronRouter) {
    def routes: HttpRoutes[IO] = patronRoutes.routes

    val httpApp: HttpApp[IO] = Router("/api" -> routes).orNotFound
}
