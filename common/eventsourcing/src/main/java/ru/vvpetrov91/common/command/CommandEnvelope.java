package ru.vvpetrov91.common.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandEnvelope<T> {
    private String commandId;
    private String commandType;
    private LocalDateTime commandIssuingTime;
    private String commandAuthor;
    private String commandContext;
    private String commandVersion;
    private T commandPayload;
}
