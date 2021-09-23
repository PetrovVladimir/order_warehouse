create table order_event
(
    event_id                varchar(255) primary key,
    event_author            varchar(255),
    event_entity_id         varchar(255),
    event_occurring_context varchar(255),
    event_occurring_time    timestamp,
    event_payload           jsonb,
    event_type              varchar(255),
    event_version           varchar(255)
);