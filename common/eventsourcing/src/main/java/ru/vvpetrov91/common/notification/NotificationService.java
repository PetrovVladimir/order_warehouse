package ru.vvpetrov91.common.notification;

import ru.vvpetrov91.common.event.DomainEventEnvelope;

public interface NotificationService<T extends DomainEventEnvelope> {
    void send(T event) throws NotificationServiceException;
}