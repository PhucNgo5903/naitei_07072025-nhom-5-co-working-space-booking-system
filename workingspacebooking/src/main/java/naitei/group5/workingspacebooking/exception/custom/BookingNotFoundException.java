package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class BookingNotFoundException extends ApiException {
    public BookingNotFoundException() {
        super(ErrorCode.BOOKING_NOT_FOUND);
    }
}
