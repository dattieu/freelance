package geny.service.serviceimpl;

import geny.common.enumtype.PromotionTypeEnum;
import geny.converter.DtoConverter;
import geny.common.enumtype.ClientActionTypeEnum;
import geny.exception.BusinessException;
import geny.persistence.dao.LoyaltyDao;
import geny.service.dto.LoyaltyInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import geny.persistence.entity.Loyalty;
import geny.resource.dto.LoyaltyRequest;
import geny.service.serviceintf.LoyaltyService;

import java.util.UUID;

/**
 * Created by dat on 1/15/2018.
 */

@Service("loyaltyService")
public class LoyaltyServiceBean extends BaseLoyaltyBean implements LoyaltyService {

    @Autowired
    private LoyaltyDao loyaltyDao;

    // TODO combine promotion type
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public LoyaltyInformation updateLoyaltyPoints(final LoyaltyRequest loyaltyRequest) {
        assertValidLoyaltyRequest(loyaltyRequest);
        Loyalty loyalty;

        if (ClientActionTypeEnum.isEverydayUseClientAction(loyaltyRequest.getClientActionType())) {
            assertValidClientActionWithProductType(loyaltyRequest.getProductType(), loyaltyRequest.getClientActionType());
            loyalty = updateEverydayUseLoyalty(loyaltyRequest);
        }
        else {
            // TODO what about occasions related to birthdays, anniversaries, annual aggregation ?
            loyalty = updateBankAppInteractionLoyalty(loyaltyRequest);
        }

        if (PromotionTypeEnum.isInCampaign(loyaltyRequest.getPromotion())) {
            loyalty = addPromotionLoyaltyPoints(loyaltyRequest, loyalty);
        }
        return DtoConverter.toLoyaltyInformation(loyalty);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public LoyaltyInformation findLoyaltyByClient(final UUID clientUuid) {
        final Loyalty loyalty = loyaltyDao.findLoyaltyByClient(clientUuid);

        if (loyalty == null) {
            throw BusinessException.loyaltyNotFoundException();
        }
        return DtoConverter.toLoyaltyInformation(loyaltyDao.findLoyaltyByClient(clientUuid));
    }
}
