package geny.persistence.dao;

import geny.persistence.entity.LoyaltyTransaction;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by dat on 1/27/2018.
 */
public interface LoyaltyTransactionDao extends BaseDao<LoyaltyTransaction, UUID> {
    LoyaltyTransaction findLoyaltyTransactionById(final UUID transactionId);
    List<LoyaltyTransaction> findLoyaltyTransactionsByClient(final UUID clientId);
    int findNumberOfClientsMakeTransactionsWithinADay(final Date date);
}
