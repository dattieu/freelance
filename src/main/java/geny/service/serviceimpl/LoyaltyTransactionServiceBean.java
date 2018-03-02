package geny.service.serviceimpl;

import geny.exception.BusinessException;
import geny.persistence.dao.LoyaltyTransactionDao;
import geny.persistence.entity.LoyaltyTransaction;
import geny.resource.dto.LoyaltyRequest;
import geny.service.serviceintf.LoyaltyTransactionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * Created by dat on 1/27/2018.
 */

@Service("loyaltyTransactionService")
public class LoyaltyTransactionServiceBean extends BaseServiceImpl<LoyaltyTransaction, UUID> implements LoyaltyTransactionService {

    private final Logger logger = Logger.getLogger(LoyaltyTransactionServiceBean.class);

    @Autowired
    private LoyaltyTransactionDao loyaltyTransactionDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void persistLoyaltyTransaction(final LoyaltyRequest loyaltyRequest) {
        LoyaltyTransaction loyaltyTransaction = loyaltyTransactionDao.findLoyaltyTransaction(loyaltyRequest.getTransactionId());
        if (loyaltyTransaction != null) {
            throw BusinessException.loyaltyTransactionConflict();
        }

        loyaltyTransaction = new LoyaltyTransaction();
        loyaltyTransaction.setPhoneNumber(loyaltyRequest.getPhoneNumber());
        loyaltyTransaction.setTransactionId(loyaltyRequest.getTransactionId());
        loyaltyTransaction.setClientId(loyaltyRequest.getClientId());
        loyaltyTransaction.setClientActionType(loyaltyRequest.getClientActionType());
        loyaltyTransaction.setProductType(loyaltyRequest.getProductType());
        loyaltyTransaction.setPromotion(loyaltyRequest.getPromotion());
        loyaltyTransaction.setCrossBorderTransaction(loyaltyRequest.isCrossBorderTransaction());
        loyaltyTransaction.setTransactionAmount(loyaltyRequest.getTransactionAmount());
        loyaltyTransaction.setReversalLoyalty(loyaltyRequest.isReversalLoyalty());
        loyaltyTransaction.setCreatedAt(new Date());

        loyaltyTransactionDao.persist(loyaltyTransaction);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public int findNumberOfClientsMakeTransactionsWithinADay() {
        return loyaltyTransactionDao.findNumberOfClientsMakeTransactionsToday();
    }
}
