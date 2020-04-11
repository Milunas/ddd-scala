package com.milunas.scaladdd.library.commons.events

trait DomainEventPublisher {
  def publish(event: DomainEvent): Either[Throwable, Unit]

  def publishAll(events: List[DomainEvent]) = events.foreach(publish)
}
