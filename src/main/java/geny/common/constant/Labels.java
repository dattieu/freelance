package geny.common.constant;

/**
 * Created by dat on 1/23/2018.
 */
public final class Labels {
    // redemption
    public static final String TRAVEL_REDEMPTION = "GRAB_AND_UBER";
    public static final String ENTERTAINMENT_REDEMPTION = "CGV_TICKET";
    public static final String LAUNCHING_CAMPAIGN_REDEMPTION = "COFFEE_VOUCHER";
    public static final String SUMMER_CAMPAIGN_REDEMPTION = "SINGAPORE_TICKET";

    // resources related
    public static final String LOYALTY_SERVICE_RESOURCE_VERSION = "v1";
    public static final String LOYALTY_REDEMPTION = "/redemption";

    // TODO move this into a separate file just for exceptions
    // exceptions related
    public static final String INTERNAL_SERVER_ERROR = "Something wrong happened in the server";
    public static final String INVALID_LOYALTY_REQUEST = "Invalid loyalty request";
    public static final String INVALID_CLIENT_ACTION_TYPE = "Invalid client action type";
    public static final String INVALID_EVERYDAY_USE_TRANSACTION_AMOUNT = "Transaction amount is empty";
    public static final String CLIENT_ACTION_NOT_SUPPORTED_WITH_PRODUCT_TYPE = "Invalid client action with this product";
    public static final String LOYALTY_NOT_FOUND = "Loyalty not found";
    public static final String INVALID_LOYALTY_TRANSACTION_ID = "Loyalty transaction id already exists";

    // error code
    public static final String INTERNAL_SERVER_ERROR_CODE = "502_INTERNAL_SERVER_ERROR";
    public static final String INVALID_LOYALTY_REQUEST_CODE = "400_LOYALTY_REQ";
    public static final String INVALID_CLIENT_ACTION_TYPE_CODE = "400_CLIENT_ACTION";
    public static final String INVALID_EVERYDAY_USE_TRANSACTION_AMOUNT_CODE = "400_TRANSACTION_AMOUNT";
    public static final String CLIENT_ACTION_NOT_SUPPORTED_WITH_PRODUCT_TYPE_CODE = "400_ACTION_UNSUPPORTED";
    public static final String LOYALTY_NOT_FOUND_CODE = "404_LOYALTY_NOT_FOUND";
    public static final String INVALID_LOYALTY_TRANSACTION_ID_CODE = "409_LOYALTY_TRANS_CONFLICT";
}
