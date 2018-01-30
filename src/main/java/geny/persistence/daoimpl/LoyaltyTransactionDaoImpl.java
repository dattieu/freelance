package geny.persistence.daoimpl;

import geny.persistence.dao.LoyaltyTransactionDao;
import geny.persistence.entity.LoyaltyTransaction;
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
    public List<LoyaltyTransaction> findLoyaltyTransactionsByClient(final UUID clientId) {
        return getCriteria().add(Restrictions.eq("client_id", clientId)).list();
    }

    @Override
    public int findNumberOfClientsMakeTransactionsWithinADay(final Date date) {
        String queryString = "SELECT COUNT(DISTINCT client_id) FROM transaction WHERE creation_date = :date";
        SQLQuery query = getSession().createSQLQuery(queryString);
        query.setParameter("date", date);
        return ((BigInteger) query.uniqueResult()).intValue();
    }
}
