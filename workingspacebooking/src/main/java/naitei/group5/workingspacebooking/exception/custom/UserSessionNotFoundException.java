package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class UserSessionNotFoundException extends ApiException {
    public UserSessionNotFoundException() {
        super(ErrorCode.USER_SESSION_NOT_FOUND);
    }
}
