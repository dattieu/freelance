package geny.common.enumtype;

import geny.common.constant.Constants;

import java.math.BigDecimal;

/**
 * Created by dat on 3/2/2018.
 */
public enum RedemptionTypeEnum {
    GRAB_AND_UBER(RedemptionKindEnum.TRAVEL, Constants.GRAB_UBER_VOUCHER),
    CGV_TICKET(RedemptionKindEnum.ENTERTAINMENT, Constants.CGV_TICKET_VOUCHER),
    COFFEE_VOUCHER(RedemptionKindEnum.PROMOTION, Constants.LAUNCHING_CAMPAIGN_COFFEE_VOUCHER),
    SINGAPORE_TICKET(RedemptionKindEnum.PROMOTION, Constants.SUMMER_CAMPAIGN_TRIP_VOUCHER);

    private enum RedemptionKindEnum {
        TRAVEL,
        ENTERTAINMENT,
        PROMOTION
    }

    private final RedemptionKindEnum redemptionKind;
    private final BigDecimal redemptionValue;

    RedemptionTypeEnum(RedemptionKindEnum redemptionKind, BigDecimal redemptionValue) {
        this.redemptionKind = redemptionKind;
        this.redemptionValue = redemptionValue;
    }

    public final RedemptionKindEnum getRedemptionKind() {
        return redemptionKind;
    }

    public final BigDecimal getRedemptionValue() {
        return redemptionValue;
    }
}
