package geny.service.dto;

import java.util.Date;
import java.util.UUID;

/**
 * Created by dat on 1/16/2018.
 */
public class LoyaltyInformation {

    private UUID clientId;

    private int loyaltyPoint;

    private Date updatedAt;

    public UUID getClientId() {
        return clientId;
    }

    public LoyaltyInformation setClientId(UUID clientId) {
        this.clientId = clientId;
        return this;
    }

    public int getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public LoyaltyInformation setLoyaltyPoint(int loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public LoyaltyInformation setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
