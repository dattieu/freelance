package geny.converter;

import geny.persistence.entity.Loyalty;
import geny.resource.dto.LoyaltyResponse;
import geny.service.dto.LoyaltyInformation;

/**
 * Created by dat on 1/16/2018.
 */
public final class DtoConverter {

    public static final LoyaltyResponse toLoyaltyResponse(LoyaltyInformation loyaltyInformation) {
        return new LoyaltyResponse().setLoyaltyInformation(loyaltyInformation);
    }

    public static final LoyaltyInformation toLoyaltyInformation(Loyalty loyalty) {
        return new LoyaltyInformation().setClientId(loyalty.getClientId())
                .setLoyaltyPoint(loyalty.getPoint())
                .setUpdatedAt(loyalty.getUpdatedAt());
    }
}
