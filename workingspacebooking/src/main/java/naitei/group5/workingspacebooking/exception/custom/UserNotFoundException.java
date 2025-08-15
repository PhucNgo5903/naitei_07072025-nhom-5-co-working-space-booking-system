package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
