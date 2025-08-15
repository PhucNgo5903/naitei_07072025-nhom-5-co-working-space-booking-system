package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class OtpInvalidException extends ApiException {
    public OtpInvalidException() {
        super(ErrorCode.OTP_INVALID);
    }
}
