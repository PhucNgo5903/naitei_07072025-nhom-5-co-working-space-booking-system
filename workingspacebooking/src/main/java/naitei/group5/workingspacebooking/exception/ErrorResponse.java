package naitei.group5.workingspacebooking.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String error,
        String message,
        LocalDateTime timestamp
) {
    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.getStatus().value(),
                errorCode.getStatus().name(),
                errorCode.getMessage(),
                LocalDateTime.now()
        );
    }
}
