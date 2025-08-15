package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class VenueStyleNotFoundException extends ApiException {
    public VenueStyleNotFoundException() {
        super(ErrorCode.VENUE_STYLE_NOT_FOUND);
    }
}
