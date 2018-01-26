package geny.common.utils;

import geny.common.constant.Constants;
import geny.common.enumtype.ClientActionTypeEnum;
import geny.common.enumtype.ProductTypeEnum;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dat on 1/21/2018.
 */
public final class LoyaltyPointMapper {
    // map the client action to the equivalent loyalty points
    private static final Map<ClientActionTypeEnum, Integer> BANK_APP_MAPPER = new HashMap<>();

    // map the production type to the equivalent transaction amount. For example, every 5.000 VND transaction
    // of Yo! Debit card (so called 'primary amount')(both Retail or Sale) equals to 1 loyalty point
    private static final Map<ProductTypeEnum, BigDecimal> EVERYDAY_USE_MONEY_MAPPER = new HashMap<>();

    public static Map<ClientActionTypeEnum, Integer> getBankAppInteractionMapper() {
        return BANK_APP_MAPPER;
    }
    public static Map<ProductTypeEnum, BigDecimal> getEverydayUseMoneyMapper(){
        return EVERYDAY_USE_MONEY_MAPPER;
    }

    static {
        BANK_APP_MAPPER.put(ClientActionTypeEnum.KYC_PROCESS_FULFILLMENT, Constants.TEN_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.YO_PLUS_APPROVAL, Constants.TEN_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.FIRST_TRIP_PLAN, Constants.FIVE_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.NEXT_TRIP_PLAN, Constants.TWO_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.PLANNED_TRIP_COMPLETION, Constants.TEN_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.ON_TIME_YO_PLUS_PAYMENT, Constants.ONE_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.ON_TIME_YO_PLUS_CREDIT_CARD_PAYMENT, Constants.ONE_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.FIRST_YO_DEBIT_TRANSACTION, Constants.ONE_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.ANNUAL_HUNDRED_MILLION_VND_TRANSACTION, Constants.ONE_HUNDRED_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.CUSTOMER_BIRTHDAY, Constants.TEN_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.ANNUAL_CARD_ANNIVERSARY, Constants.FIVE_THOUSAND_POINTS);
        BANK_APP_MAPPER.put(ClientActionTypeEnum.REFERRED_FRIEND_KYC_PROCESS_FULFILLMENT, Constants.FIVE_THOUSAND_POINTS);

        EVERYDAY_USE_MONEY_MAPPER.put(ProductTypeEnum.YO_DEBIT_CARD, Constants.FIVE_THOUSAND_VND);
        EVERYDAY_USE_MONEY_MAPPER.put(ProductTypeEnum.YO_CREDIT_CARD, Constants.FIVE_THOUSAND_VND);
        EVERYDAY_USE_MONEY_MAPPER.put(ProductTypeEnum.YO_PLUS, Constants.ONE_MILLION_VND);
        EVERYDAY_USE_MONEY_MAPPER.put(ProductTypeEnum.YO_ACCOUNT, Constants.ONE_MILLION_VND);
    }
}
