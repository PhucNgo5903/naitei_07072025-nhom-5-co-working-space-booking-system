package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class FavouriteStyleNotFoundException extends ApiException {
    public FavouriteStyleNotFoundException() {
        super(ErrorCode.FAVOURITE_STYLE_NOT_FOUND);
    }
}
