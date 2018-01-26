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
    public final Loyalty findLoyaltyByClient(final UUID clientUuid) {
        return find(clientUuid);
    }
}
