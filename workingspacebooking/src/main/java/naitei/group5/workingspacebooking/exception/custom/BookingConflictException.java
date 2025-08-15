package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class BookingConflictException extends ApiException {
    public BookingConflictException() {
        super(ErrorCode.BOOKING_CONFLICT);
    }
}
