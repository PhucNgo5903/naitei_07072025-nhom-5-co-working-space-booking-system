package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class AccountRecoveryNotFoundException extends ApiException {
    public AccountRecoveryNotFoundException() {
        super(ErrorCode.ACCOUNT_RECOVERY_NOT_FOUND);
    }
}
