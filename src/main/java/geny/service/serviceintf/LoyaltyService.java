package geny.service.serviceintf;

import geny.persistence.entity.Loyalty;
import geny.resource.dto.LoyaltyRequest;
import geny.service.dto.LoyaltyInformation;

import java.util.UUID;

/**
 * Created by dat on 1/15/2018.
 */
public interface LoyaltyService extends BaseService<Loyalty, UUID> {
    LoyaltyInformation updateLoyaltyPoints(final LoyaltyRequest loyaltyRequest);
    LoyaltyInformation findLoyaltyByClient(final String phoneNumber);
}
