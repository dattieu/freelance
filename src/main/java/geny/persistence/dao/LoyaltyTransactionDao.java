package geny.persistence.dao;

import geny.persistence.entity.LoyaltyTransaction;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by dat on 1/27/2018.
 */
public interface LoyaltyTransactionDao extends BaseDao<LoyaltyTransaction, UUID> {
    List<LoyaltyTransaction> findLoyaltyTransactionsByClient(final String phoneNumber);
    LoyaltyTransaction findLoyaltyTransaction(final UUID transactionUuid);
    int findNumberOfClientsMakeTransactionsToday();
}
