package com.milunas.scaladdd.library.commons.events

class StoreAndForwardPublisher extends DomainEventPublisher {
  override def publish(event: DomainEvent): Either[Throwable, Unit] = ???
}
