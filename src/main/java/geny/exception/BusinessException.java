package geny.exception;

import geny.common.constant.Labels;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

/**
 * Created by dat on 1/21/2018.
 */
public class BusinessException extends RestServerException {
    private BusinessException(String message, HttpStatus httpStatus, List<String> errorList) {
        super(message, httpStatus, errorList);
    }

    private static final BusinessException businessException(String message, HttpStatus httpStatus, List<String> errorList) {
        return new BusinessException(message, httpStatus, errorList);
    }

    public static final BusinessException invalidClientActionTypeException() {
        return businessException(Labels.INVALID_CLIENT_ACTION_TYPE, HttpStatus.BAD_REQUEST, Collections.emptyList());
    }

    public static final BusinessException invalidTransactionAmountException() {
        return businessException(Labels.INVALID_EVERYDAY_USE_TRANSACTION_AMOUNT, HttpStatus.BAD_REQUEST, Collections.emptyList());
    }

    public static final BusinessException inapproriateClientActionWithProductTypeException() {
        return businessException(Labels.CLIENT_ACTION_NOT_SUPPORTED_WITH_PRODUCT_TYPE, HttpStatus.CONFLICT, Collections.emptyList());
    }

    public static final BusinessException loyaltyNotFoundException() {
        return businessException(Labels.LOYALTY_NOT_FOUND, HttpStatus.NOT_FOUND, Collections.emptyList());
    }
}
