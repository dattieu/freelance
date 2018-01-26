package geny.resource.dto;

import geny.service.dto.LoyaltyInformation;

import java.util.Date;

/**
 * Created by dat on 1/15/2018.
 */
public class LoyaltyResponse {

    private LoyaltyInformation loyaltyInformation;

    private Date transactionDate;

    public LoyaltyResponse() {
        this.transactionDate = new Date();
    }

    public LoyaltyInformation getLoyaltyInformation() {
        return loyaltyInformation;
    }

    public LoyaltyResponse setLoyaltyInformation(LoyaltyInformation loyaltyInformation) {
        this.loyaltyInformation = loyaltyInformation;
        return this;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public LoyaltyResponse setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }
}
