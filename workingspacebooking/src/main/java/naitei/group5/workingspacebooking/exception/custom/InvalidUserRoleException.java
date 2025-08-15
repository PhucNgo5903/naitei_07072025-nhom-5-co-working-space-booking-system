package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class InvalidUserRoleException extends ApiException {
    public InvalidUserRoleException() {
        super(ErrorCode.INVALID_USER_ROLE);
    }
}
