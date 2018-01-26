package geny.service.serviceimpl;

import geny.common.enumtype.ClientActionTypeEnum;
import geny.common.enumtype.ProductTypeEnum;
import geny.common.enumtype.PromotionTypeEnum;
import geny.exception.BusinessException;
import geny.persistence.dao.LoyaltyDao;
import geny.persistence.dao.LoyaltyTransactionDao;
import geny.persistence.entity.Loyalty;
import geny.resource.dto.LoyaltyRequest;
import geny.common.utils.LoyaltyPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dat on 1/22/2018.
 */

@Service("baseLoyaltyService")
@Transactional(propagation = Propagation.REQUIRED)
public class BaseLoyaltyBean extends BaseServiceImpl<Loyalty, UUID> {
    private static final int MAX_CARD_HOLDERS_PER_DAY = 100;

    @Autowired
    private LoyaltyDao loyaltyDao;

    @Autowired
    private LoyaltyTransactionDao loyaltyTransactionDao;

    // do some assertions for loyalty request, client action, product type and transaction amount
    protected void assertValidLoyaltyRequest(final LoyaltyRequest loyaltyRequest) {
        assertValidClientActionType(loyaltyRequest.getClientActionType());
        assertValidTransactionAmount(loyaltyRequest.getClientActionType(), loyaltyRequest.getTransactionAmount());
    }

    private void assertValidClientActionType(final ClientActionTypeEnum clientActionType) {
        if (!ClientActionTypeEnum.isValidClientActionType(clientActionType)) {
            throw BusinessException.invalidClientActionTypeException();
        }
    }

    private void assertValidTransactionAmount(final ClientActionTypeEnum clientActionType,
                                              final BigDecimal transactionAmount) {
        if (ClientActionTypeEnum.isEverydayUseClientAction(clientActionType) && transactionAmount == null) {
            throw BusinessException.invalidTransactionAmountException();
        }
    }

    protected void assertValidClientActionWithProductType(final ProductTypeEnum productType,
                                                          final ClientActionTypeEnum clientActionType) {
        if (!ProductTypeEnum.isValidClientActionWithProductType(productType, clientActionType)) {
            throw BusinessException.inapproriateClientActionWithProductTypeException();
        }
    }

    // every day use loyalty
    protected Loyalty updateEverydayUseLoyalty(final LoyaltyRequest loyaltyRequest) {
        final Loyalty loyalty = loyaltyDao.findLoyaltyByClient(loyaltyRequest.getClientId());
        return loyalty == null ? createNewEverydayUseLoyalty(loyaltyRequest)
                : updateCurrentEverydayUseLoyalty(loyaltyRequest, loyalty);
    }

    private Loyalty createNewEverydayUseLoyalty(final LoyaltyRequest loyaltyRequest) {
        final Loyalty loyalty = setBasicFieldsForLoyalty(loyaltyRequest);
        loyalty.setPoint(calculateLoyaltyPointsForEverydayUse(loyaltyRequest, 0));
        return loyalty;
    }

    private Loyalty updateCurrentEverydayUseLoyalty(final LoyaltyRequest loyaltyRequest, final Loyalty loyalty) {
        loyalty.setPoint(calculateLoyaltyPointsForEverydayUse(loyaltyRequest, loyalty.getPoint()));
        loyalty.setUpdatedAt(new Date());
        return loyalty;
    }

    private int calculateLoyaltyPointsForEverydayUse(final LoyaltyRequest loyaltyRequest, final int currentPoint) {
        return loyaltyRequest.getTransactionAmount()
                .divide(LoyaltyPointMapper.getEverydayUseMoneyMapper().get(loyaltyRequest.getProductType()), 0)
                .intValueExact()
                + currentPoint;
    }

    // bank and app interaction loyalty
    protected Loyalty updateBankAppInteractionLoyalty(final LoyaltyRequest loyaltyRequest) {
        final Loyalty loyalty = loyaltyDao.findLoyaltyByClient(loyaltyRequest.getClientId());
        return loyalty == null ? createNewBankAppInteractionLoyalty(loyaltyRequest)
                : updateCurrentBankAppInteractionLoyalty(loyaltyRequest, loyalty);
    }

    private Loyalty createNewBankAppInteractionLoyalty(final LoyaltyRequest loyaltyRequest) {
        Map<ClientActionTypeEnum, Integer> loyaltyMapper = LoyaltyPointMapper.getBankAppInteractionMapper();

        final Loyalty loyalty = setBasicFieldsForLoyalty(loyaltyRequest);
        loyalty.setPoint(loyaltyMapper.get(loyaltyRequest.getClientActionType()));
        loyaltyDao.persist(loyalty);
        return loyalty;
    }

    private Loyalty updateCurrentBankAppInteractionLoyalty(final LoyaltyRequest loyaltyRequest, Loyalty loyalty) {
        Map<ClientActionTypeEnum, Integer> loyaltyMapper = LoyaltyPointMapper.getBankAppInteractionMapper();

        loyalty.setPoint(loyaltyMapper.get(loyaltyRequest.getClientActionType()) + loyalty.getPoint());
        loyalty.setUpdatedAt(new Date());
        return loyaltyDao.merge(loyalty);
    }

    // promotion loyalty points
    protected Loyalty addPromotionLoyaltyPoints(final LoyaltyRequest loyaltyRequest, final Loyalty loyalty) {
        if (PromotionTypeEnum.isOfLaunchingCampaign(loyaltyRequest.getPromotion()) && isClientStillAmongFirstCardHolders()) {
            loyalty.setPoint(loyalty.getPoint() * 2);
        }

        if (PromotionTypeEnum.isOfSummerCampaign(loyaltyRequest.getPromotion())
                && ProductTypeEnum.YO_DEBIT_CARD.equals(loyaltyRequest.getProductType())
                && loyaltyRequest.isCrossBorderTransaction()) {
            loyalty.setPoint(loyalty.getPoint() * 10);
        }
        return loyalty;
    }

    private boolean isClientStillAmongFirstCardHolders() {
        return loyaltyTransactionDao.findNumberOfClientsMakeTransactionsWithinADay(new Date()) < MAX_CARD_HOLDERS_PER_DAY;
    }

    private Loyalty setBasicFieldsForLoyalty(final LoyaltyRequest loyaltyRequest) {
        final Loyalty loyalty = new Loyalty();
        loyalty.setClientId(loyaltyRequest.getClientId());
        loyalty.setUpdatedAt(new Date());
        return loyalty;
    }
}
