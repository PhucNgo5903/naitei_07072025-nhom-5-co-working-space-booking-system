package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class VenueAlreadyExistsException extends ApiException {
    public VenueAlreadyExistsException() {
        super(ErrorCode.VENUE_ALREADY_EXISTS);
    }
}
