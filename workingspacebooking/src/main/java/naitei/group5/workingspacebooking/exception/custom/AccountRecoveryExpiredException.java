package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class AccountRecoveryExpiredException extends ApiException {
    public AccountRecoveryExpiredException() {
        super(ErrorCode.ACCOUNT_RECOVERY_EXPIRED);
    }
}
