package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class InvalidPaymentStatusException extends ApiException {
    public InvalidPaymentStatusException() {
        super(ErrorCode.INVALID_PAYMENT_STATUS);
    }
}
