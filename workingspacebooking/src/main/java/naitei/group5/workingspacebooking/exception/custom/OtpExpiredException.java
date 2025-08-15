package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class OtpExpiredException extends ApiException {
    public OtpExpiredException() {
        super(ErrorCode.OTP_EXPIRED);
    }
}
