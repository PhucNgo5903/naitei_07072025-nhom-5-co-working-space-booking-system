package naitei.group5.workingspacebooking.exception.custom;

import naitei.group5.workingspacebooking.exception.ApiException;
import naitei.group5.workingspacebooking.exception.ErrorCode;

public class PaymentNotFoundException extends ApiException {
    public PaymentNotFoundException() {
        super(ErrorCode.PAYMENT_NOT_FOUND);
    }
}
