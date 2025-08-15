package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class VenueNotVerifiedException extends ApiException {
    public VenueNotVerifiedException() {
        super(ErrorCode.VENUE_NOT_VERIFIED);
    }
}
