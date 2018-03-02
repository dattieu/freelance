package geny.service.serviceintf;

import geny.persistence.entity.LoyaltyTransaction;
import geny.resource.dto.LoyaltyRequest;

import java.util.Date;
import java.util.UUID;

/**
 * Created by dat on 1/27/2018.
 */
public interface LoyaltyTransactionService extends BaseService<LoyaltyTransaction, UUID> {
    void persistLoyaltyTransaction(final LoyaltyRequest loyaltyRequest);
    int findNumberOfClientsMakeTransactionsWithinADay();
}
