package naitei.group5.workingspacebooking.exception.custom;


import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class VenueNotFoundException extends ApiException {
    public VenueNotFoundException() {
        super(ErrorCode.VENUE_NOT_FOUND);
    }
}
