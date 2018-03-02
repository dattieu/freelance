package geny.persistence.daoimpl;

import geny.persistence.dao.LoyaltyTransactionDao;
import geny.persistence.entity.LoyaltyTransaction;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by dat on 1/27/2018.
 */

@Repository("loyaltyTransactionDao")
public class LoyaltyTransactionDaoImpl extends BaseDaoImpl<LoyaltyTransaction, UUID> implements LoyaltyTransactionDao {
    @SuppressWarnings("unchecked")
    @Override
    public List<LoyaltyTransaction> findLoyaltyTransactionsByClient(final String phoneNumber) {
        return getCriteria().add(Restrictions.eq("phone", phoneNumber)).list();
    }

    @Override
    public LoyaltyTransaction findLoyaltyTransaction(final UUID transactionUuid) {
        return (LoyaltyTransaction) getCriteria().add(Restrictions.eq("transactionId", transactionUuid)).uniqueResult();
    }

    @Override
    public int findNumberOfClientsMakeTransactionsToday() {
        final Date today = DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH);
        String queryString = "SELECT COUNT(DISTINCT phone) FROM transaction WHERE creation_date = :today";
        SQLQuery query = getSession().createSQLQuery(queryString);
        query.setParameter("today", today);
        return ((BigInteger) query.uniqueResult()).intValue();
    }
}
