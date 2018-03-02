package geny.service.serviceimpl;

import geny.common.enumtype.ClientActionTypeEnum;
import geny.common.enumtype.ProductTypeEnum;
import geny.common.enumtype.PromotionTypeEnum;
import geny.common.enumtype.RedemptionTypeEnum;
import geny.common.utils.RedemptionMapper;
import geny.exception.BusinessException;
import geny.persistence.dao.LoyaltyDao;
import geny.persistence.entity.Loyalty;
import geny.persistence.entity.Redemption;
import geny.resource.dto.LoyaltyRequest;
import geny.common.utils.LoyaltyPointMapper;
import geny.service.serviceintf.LoyaltyTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dat on 1/22/2018.
 */

@Service("baseLoyaltyService")
@Transactional(propagation = Propagation.REQUIRED)
public class BaseLoyaltyBean extends BaseServiceImpl<Loyalty, UUID> {
    private static final int MAX_CARD_HOLDERS_PER_DAY = 100;
    private static int loyaltyCurrentPoint = 0;

    @Autowired
    private LoyaltyDao loyaltyDao;

    @Autowired
    private LoyaltyTransactionService loyaltyTransactionService;

    // store every loyalty transaction for later use
    protected void persistLoyaltyTransaction(final LoyaltyRequest loyaltyRequest) {
        loyaltyTransactionService.persistLoyaltyTransaction(loyaltyRequest);
    }

    // do some assertions for loyalty request, client action, product type and transaction amount
    protected void assertValidLoyaltyRequest(final LoyaltyRequest loyaltyRequest) {
        assertNotReversalLoyalty(loyaltyRequest.isReversalLoyalty());
        assertValidClientActionType(loyaltyRequest.getClientActionType());
        assertValidTransactionAmount(loyaltyRequest.getClientActionType(), loyaltyRequest.getTransactionAmount());
    }

    private void assertNotReversalLoyalty(final boolean isReversalLoyalty) {
        if (isReversalLoyalty) {
            throw BusinessException.invalidLoyaltyRequest();
        }
    }

    private void assertValidClientActionType(final ClientActionTypeEnum clientActionType) {
        if (!ClientActionTypeEnum.isValidClientActionType(clientActionType)) {
            throw BusinessException.invalidClientActionType();
        }
    }

    private void assertValidTransactionAmount(final ClientActionTypeEnum clientActionType,
                                              final BigDecimal transactionAmount) {
        if (ClientActionTypeEnum.isEverydayUseClientAction(clientActionType) && transactionAmount == null) {
            throw BusinessException.invalidTransactionAmount();
        }
    }

    protected void assertValidClientActionWithProductType(final ProductTypeEnum productType,
                                                          final ClientActionTypeEnum clientActionType) {
        if (!ProductTypeEnum.isValidClientActionWithProductType(productType, clientActionType)) {
            throw BusinessException.inappropriateClientActionWithProductType();
        }
    }

    // every day use loyalty
    protected Loyalty updateEverydayUseLoyalty(final LoyaltyRequest loyaltyRequest) {
        final Loyalty loyalty = loyaltyDao.findLoyaltyByPhone(loyaltyRequest.getPhoneNumber());
        return loyalty == null ? createNewEverydayUseLoyalty(loyaltyRequest)
                : updateCurrentEverydayUseLoyalty(loyaltyRequest, loyalty);
    }

    private Loyalty createNewEverydayUseLoyalty(final LoyaltyRequest loyaltyRequest) {
        loyaltyCurrentPoint = 0;
        final Loyalty loyalty = setBasicFieldsForLoyalty(loyaltyRequest);
        loyalty.setPoint(calculateLoyaltyPointsForEverydayUse(loyaltyRequest, 0));
        loyaltyDao.persist(loyalty);
        return loyalty;
    }

    private Loyalty updateCurrentEverydayUseLoyalty(final LoyaltyRequest loyaltyRequest, final Loyalty loyalty) {
        loyaltyCurrentPoint = loyalty.getPoint();
        loyalty.setPoint(calculateLoyaltyPointsForEverydayUse(loyaltyRequest, loyalty.getPoint()));
        loyalty.setUpdatedAt(new Date());
        return loyaltyDao.merge(loyalty);
    }

    private int calculateLoyaltyPointsForEverydayUse(final LoyaltyRequest loyaltyRequest, final int currentPoint) {
        return loyaltyRequest.getTransactionAmount()
                .divide(LoyaltyPointMapper.getEverydayUseMoneyMapper().get(loyaltyRequest.getProductType()), 0)
                .intValueExact()
                + currentPoint;
    }

    // bank and app interaction loyalty
    protected Loyalty updateBankAppInteractionLoyalty(final LoyaltyRequest loyaltyRequest) {
        final Loyalty loyalty = loyaltyDao.findLoyaltyByPhone(loyaltyRequest.getPhoneNumber());
        return loyalty == null ? createNewBankAppInteractionLoyalty(loyaltyRequest)
                : updateCurrentBankAppInteractionLoyalty(loyaltyRequest, loyalty);
    }

    private Loyalty createNewBankAppInteractionLoyalty(final LoyaltyRequest loyaltyRequest) {
        loyaltyCurrentPoint = 0;
        Map<ClientActionTypeEnum, Integer> loyaltyMapper = LoyaltyPointMapper.getBankAppInteractionMapper();

        final Loyalty loyalty = setBasicFieldsForLoyalty(loyaltyRequest);
        loyalty.setPoint(loyaltyMapper.get(loyaltyRequest.getClientActionType()));
        loyaltyDao.persist(loyalty);
        return loyalty;
    }

    private Loyalty updateCurrentBankAppInteractionLoyalty(final LoyaltyRequest loyaltyRequest, final Loyalty loyalty) {
        loyaltyCurrentPoint = loyalty.getPoint();
        Map<ClientActionTypeEnum, Integer> loyaltyMapper = LoyaltyPointMapper.getBankAppInteractionMapper();

        loyalty.setPoint(loyaltyMapper.get(loyaltyRequest.getClientActionType()) + loyalty.getPoint());
        loyalty.setUpdatedAt(new Date());
        return loyaltyDao.merge(loyalty);
    }

    // promotion loyalty points
    // x2 or x10 all the points and then minus the previous points which does not belong to the promotion
    protected Loyalty addPromotionLoyaltyPoints(final LoyaltyRequest loyaltyRequest, final Loyalty loyalty) {
        if (PromotionTypeEnum.isOfLaunchingCampaign(loyaltyRequest.getPromotion()) && isClientStillAmongFirstCardHolders()) {
            loyalty.setPoint(loyalty.getPoint() * 2 - loyaltyCurrentPoint);
        }

        if (PromotionTypeEnum.isOfSummerCampaign(loyaltyRequest.getPromotion())
                && ProductTypeEnum.YO_DEBIT_CARD.equals(loyaltyRequest.getProductType())
                && loyaltyRequest.isCrossBorderTransaction()) {
            loyalty.setPoint(loyalty.getPoint() * 10 - loyaltyCurrentPoint*9);
        }
        return loyaltyDao.merge(loyalty);
    }

    // what if a client makes many transactions? still get promotion points?
    private boolean isClientStillAmongFirstCardHolders() {
        return loyaltyTransactionService.findNumberOfClientsMakeTransactionsWithinADay() < MAX_CARD_HOLDERS_PER_DAY;
    }

    private Loyalty setBasicFieldsForLoyalty(final LoyaltyRequest loyaltyRequest) {
        final Loyalty loyalty = new Loyalty();
        loyalty.setPhoneNumber(loyaltyRequest.getPhoneNumber());
        loyalty.setClientId(loyaltyRequest.getClientId());
        loyalty.setUpdatedAt(new Date());
        return loyalty;
    }

    // redemption
    protected Loyalty updateRedemption(final Loyalty loyalty, final PromotionTypeEnum promotionType) {
        final List<Redemption> redemptionList = new ArrayList<>();
        for (RedemptionTypeEnum redemptionType : RedemptionTypeEnum.values()) {
            int redemptionQuantity = loyalty.getPoint() /
                    RedemptionMapper.getRedemptionMapper().get(redemptionType.name());
            if ((RedemptionTypeEnum.COFFEE_VOUCHER.equals(redemptionType) ||
                    RedemptionTypeEnum.SINGAPORE_TICKET.equals(redemptionType))
                    && !isValidPromotionRedemption(promotionType)) {
                redemptionQuantity = 0;
            }

            final Redemption redemption = createBasicRedemption(redemptionType);
            redemption.setQuantity(redemptionQuantity);
            redemptionList.add(redemption);
        }

        loyalty.setRedemptionList(redemptionList);
        return loyaltyDao.merge(loyalty);
    }

    private Redemption createBasicRedemption(final RedemptionTypeEnum redemptionType) {
        final Redemption redemption = new Redemption();
        redemption.setRedemptionType(redemptionType);
        redemption.setRedemptionValue(redemptionType.getRedemptionValue());
        redemption.setUpdatedAt(new Date());
        return redemption;
    }

    private boolean isValidPromotionRedemption(final PromotionTypeEnum promotionType) {
        return (PromotionTypeEnum.isOfLaunchingCampaign(promotionType) && isClientStillAmongFirstCardHolders())
                || (PromotionTypeEnum.isOfSummerCampaign(promotionType));
    }
}
