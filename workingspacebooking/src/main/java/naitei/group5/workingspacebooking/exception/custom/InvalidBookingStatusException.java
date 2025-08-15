package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class InvalidBookingStatusException extends ApiException {
    public InvalidBookingStatusException() {
        super(ErrorCode.INVALID_BOOKING_STATUS);
    }
}
