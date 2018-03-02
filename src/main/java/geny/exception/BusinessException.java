package geny.exception;

import geny.common.constant.Labels;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dat on 1/21/2018.
 */
public final class BusinessException extends RestServerException {
    private BusinessException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }

    private static final BusinessException businessException(String message, HttpStatus httpStatus, String errorCode) {
        return new BusinessException(message, httpStatus, errorCode);
    }

    public static final BusinessException invalidLoyaltyRequest() {
        return businessException(Labels.INVALID_LOYALTY_REQUEST, HttpStatus.BAD_REQUEST,
                Labels.INVALID_LOYALTY_REQUEST_CODE);
    }

    public static final BusinessException invalidClientActionType() {
        return businessException(Labels.INVALID_CLIENT_ACTION_TYPE, HttpStatus.BAD_REQUEST,
                Labels.INVALID_CLIENT_ACTION_TYPE_CODE);
    }

    public static final BusinessException invalidTransactionAmount() {
        return businessException(Labels.INVALID_EVERYDAY_USE_TRANSACTION_AMOUNT, HttpStatus.BAD_REQUEST,
                Labels.INVALID_EVERYDAY_USE_TRANSACTION_AMOUNT_CODE);
    }

    public static final BusinessException inappropriateClientActionWithProductType() {
        return businessException(Labels.CLIENT_ACTION_NOT_SUPPORTED_WITH_PRODUCT_TYPE, HttpStatus.CONFLICT,
                Labels.CLIENT_ACTION_NOT_SUPPORTED_WITH_PRODUCT_TYPE_CODE);
    }

    public static final BusinessException loyaltyNotFound() {
        return businessException(Labels.LOYALTY_NOT_FOUND, HttpStatus.NOT_FOUND,
                Labels.LOYALTY_NOT_FOUND_CODE);
    }

    public static final BusinessException loyaltyTransactionConflict() {
        return businessException(Labels.INVALID_LOYALTY_TRANSACTION_ID, HttpStatus.CONFLICT,
                Labels.INVALID_LOYALTY_TRANSACTION_ID_CODE);
    }
}
