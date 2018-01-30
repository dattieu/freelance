package geny.common.enumtype;

import geny.common.constant.Constants;

import java.time.LocalDate;

/**
 * Created by dat on 1/15/2018.
 */
public enum PromotionTypeEnum {
    LAUNCHING_CAMPAIGN(Constants.LAUNCHING_START_DATE, Constants.LAUNCHING_END_DATE),
    SUMMER_CAMPAIGN(Constants.SUMMER_START_DATE, Constants.SUMMER_END_DATE),
    OTHER_CAMPAIGN(null, null), // for future
    NO_CAMPAIGN(null, null);

    private final LocalDate startDate;
    private final LocalDate endDate;

    PromotionTypeEnum(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public final LocalDate getStartDate() {
        return startDate;
    }

    public final LocalDate getEndDate() {
        return endDate;
    }

    public static final boolean isOfLaunchingCampaign() {
        LocalDate now = LocalDate.now();
        if (now.isEqual(Constants.LAUNCHING_START_DATE) || now.isEqual(Constants.LAUNCHING_END_DATE)) {
            return true;
        }
        return now.isAfter(Constants.LAUNCHING_START_DATE) && now.isBefore(Constants.LAUNCHING_END_DATE);
    }

    public static final boolean isOfSummerCampaign() {
        LocalDate now = LocalDate.now();
        if (now.isEqual(Constants.SUMMER_START_DATE) || now.isEqual(Constants.SUMMER_END_DATE)) {
            return true;
        }
        return now.isAfter(Constants.SUMMER_START_DATE) && now.isBefore(Constants.SUMMER_END_DATE);
    }

    // TODO analyze again since core backend really not know about the promotion
    // TODO also this check is redundant
    public static final boolean isInCampaign(PromotionTypeEnum promotionType) {
        return !NO_CAMPAIGN.equals(promotionType);
    }
}
