package geny.resource.dto;

import geny.common.enumtype.ClientActionTypeEnum;
import geny.common.enumtype.ProductTypeEnum;
import geny.common.enumtype.PromotionTypeEnum;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by dat on 1/15/2018.
 */
public class LoyaltyRequest {

    @NotNull
    private UUID clientId;

    @NotNull
    private UUID transactionId;

    @NotNull
    private ProductTypeEnum productType;

    @NotNull
    private ClientActionTypeEnum clientActionType;

    // either loyalty service will check the campaign promotion or it will be passed from the core
    private PromotionTypeEnum promotion;

    @NotNull
    private boolean isCrossBorderTransaction; // what is a cross-border transaction?

    @NotNull
    private boolean isReversalLoyalty;

    // currency: VND
    private BigDecimal transactionAmount;

    public UUID getClientId() {
        return clientId;
    }

    public LoyaltyRequest setClientId(UUID clientId) {
        this.clientId = clientId;
        return this;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public ProductTypeEnum getProductType() {
        return productType;
    }

    public LoyaltyRequest setProductType(ProductTypeEnum productType) {
        this.productType = productType;
        return this;
    }

    public ClientActionTypeEnum getClientActionType() {
        return clientActionType;
    }

    public LoyaltyRequest setClientActionType(ClientActionTypeEnum clientActionType) {
        this.clientActionType = clientActionType;
        return this;
    }

    public PromotionTypeEnum getPromotion() {
        return promotion;
    }

    public LoyaltyRequest setPromotion(PromotionTypeEnum promotion) {
        this.promotion = promotion;
        return this;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public LoyaltyRequest setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public boolean isCrossBorderTransaction() {
        return isCrossBorderTransaction;
    }

    public LoyaltyRequest setCrossBorderTransaction(boolean crossBorderTransaction) {
        isCrossBorderTransaction = crossBorderTransaction;
        return this;
    }

    public boolean isReversalLoyalty() {
        return isReversalLoyalty;
    }

    public LoyaltyRequest setReversalLoyalty(boolean reversalLoyalty) {
        isReversalLoyalty = reversalLoyalty;
        return this;
    }
}
