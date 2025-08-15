package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class PaymentFailedException extends ApiException {
    public PaymentFailedException() {
        super(ErrorCode.PAYMENT_FAILED);
    }
}
