package geny.persistence.daoimpl;

import geny.persistence.dao.LoyaltyTransactionDao;
import geny.persistence.entity.LoyaltyTransaction;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by dat on 1/27/2018.
 */

@Repository("loyaltyTransactionDao")
public class LoyaltyTransactionDaoImpl extends BaseDaoImpl<LoyaltyTransaction, UUID> implements LoyaltyTransactionDao {

    @Override
    public LoyaltyTransaction findLoyaltyTransactionById(final UUID transactionId) {
        return find(transactionId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LoyaltyTransaction> findLoyaltyTransactionsByClient(final UUID clientId) {
        return getCriteria().add(Restrictions.eq("client_id", clientId)).list();
    }

    @Override
    public int findNumberOfClientsMakeTransactionsWithinADay(final Date date) {
        String queryString = "SELECT COUNT(DISTINCT client_id) FROM transaction WHERE creation_date = :date";
        SQLQuery query = getSession().createSQLQuery(queryString);
        query.setParameter(1, date);
        return (int) query.uniqueResult();
    }
}
