package geny.persistence.daoimpl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import geny.persistence.dao.LoyaltyDao;
import geny.persistence.entity.Loyalty;

import java.util.UUID;

/**
 * Created by dat on 1/15/2018.
 */

@Repository("loyaltyDao")
public class LoyaltyDaoImpl extends BaseDaoImpl<Loyalty, UUID> implements LoyaltyDao {
    @Override
    public Loyalty findLoyaltyByPhone(String phone) {
        return (Loyalty) getCriteria().add(Restrictions.eq("phoneNumber", phone)).uniqueResult();
    }
}
