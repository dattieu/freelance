package geny.persistence.dao;

import geny.persistence.entity.Loyalty;

import java.util.UUID;


/**
 * Created by dat on 1/15/2018.
 */
public interface LoyaltyDao extends BaseDao<Loyalty, UUID>  {
    Loyalty findLoyaltyByClient(final UUID clientUuid);
}
