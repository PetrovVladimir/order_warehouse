package ru.vvpetrov91.orderservice.api.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.vvpetrov91.common.event.DomainEventEnvelope;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_event")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class OrderEvent extends DomainEventEnvelope<OrderEventPayload> implements Serializable {
    public static final String EVENT_VERSION = "v1.0";
    public OrderEvent(
            String eventId,
            String eventOccurringContext,
            String eventAuthor,
            String eventType,
            String eventVersion,
            LocalDateTime eventOccurringTime,
            String eventEntityId,
            OrderEventPayload eventPayload
    ) {
        super(
                eventId,
                eventOccurringContext,
                eventAuthor,
                eventType,
                eventVersion,
                eventOccurringTime,
                eventEntityId,
                eventPayload
        );
    }
}