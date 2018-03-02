package geny.common.utils;

import geny.common.constant.Constants;
import geny.common.constant.Labels;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dat on 2/25/2018.
 */
public final class RedemptionMapper {

    private static final Map<String, Integer> REDEMPTION_MAPPER = new HashMap<>();

    public static Map<String, Integer> getRedemptionMapper() {
        return REDEMPTION_MAPPER;
    }

    static {
        REDEMPTION_MAPPER.put(Labels.TRAVEL_REDEMPTION, Constants.THREE_THOUSAND_POINTS);
        REDEMPTION_MAPPER.put(Labels.ENTERTAINMENT_REDEMPTION, Constants.THREE_THOUSAND_POINTS);
        REDEMPTION_MAPPER.put(Labels.LAUNCHING_CAMPAIGN_REDEMPTION, Constants.ONE_THOUSAND_POINTS);
        REDEMPTION_MAPPER.put(Labels.SUMMER_CAMPAIGN_REDEMPTION, Constants.ONE_MILLION_POINTS);
    }
}
