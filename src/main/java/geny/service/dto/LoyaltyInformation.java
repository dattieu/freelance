package geny.service.dto;

import geny.persistence.entity.Redemption;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by dat on 1/16/2018.
 */
public class LoyaltyInformation {

    private String phoneNumber;

    private UUID clientId;

    private int loyaltyPoint;

    private List<Redemption> redemptionList;

    private Date updatedAt;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LoyaltyInformation setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

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

    public List<Redemption> getRedemptionList() {
        return redemptionList;
    }

    public LoyaltyInformation setRedemptionList(List<Redemption> redemptionList) {
        this.redemptionList = redemptionList;
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
