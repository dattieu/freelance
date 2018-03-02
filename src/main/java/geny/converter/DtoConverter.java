package geny.converter;

import geny.persistence.entity.Loyalty;
import geny.persistence.entity.Redemption;
import geny.resource.dto.LoyaltyResponse;
import geny.service.dto.LoyaltyInformation;

/**
 * Created by dat on 1/16/2018.
 */
public final class DtoConverter {

    public static final LoyaltyResponse toLoyaltyResponse(final LoyaltyInformation loyaltyInformation) {
        return new LoyaltyResponse().setLoyaltyInformation(loyaltyInformation);
    }

    public static final LoyaltyInformation toLoyaltyInformation(final Loyalty loyalty) {
        return new LoyaltyInformation().setPhoneNumber(loyalty.getPhoneNumber())
                .setClientId(loyalty.getClientId())
                .setLoyaltyPoint(loyalty.getPoint())
                .setRedemptionList(loyalty.getRedemptionList())
                .setUpdatedAt(loyalty.getUpdatedAt());
    }
}
