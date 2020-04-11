package com.milunas.scaladdd.library.commons.events

class JustForwardPublisher extends DomainEventPublisher {
  override def publish(event: DomainEvent): Either[Throwable, Unit] = ???
}
