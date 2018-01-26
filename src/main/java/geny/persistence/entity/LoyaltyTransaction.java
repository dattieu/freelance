package geny.persistence.entity;

import geny.common.enumtype.ClientActionTypeEnum;
import geny.common.enumtype.ProductTypeEnum;
import geny.common.enumtype.PromotionTypeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class LoyaltyTransaction extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "transaction_id", nullable = false, unique = true)
    private UUID transactionId;

    @NotNull
    @Column(name = "client_id")
    private UUID clientId;

    @NotNull
    @Column(name = "product")
    private ProductTypeEnum productType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "client_action")
    private ClientActionTypeEnum clientActionType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "promotion")
    private PromotionTypeEnum promotion;

    @NotNull
    @Column(name = "is_cross_border")
    private boolean isCrossBorderTransaction;

    @Column(name = "amount")
    private BigDecimal transactionAmount;

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public ProductTypeEnum getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEnum productType) {
        this.productType = productType;
    }

    public ClientActionTypeEnum getClientActionType() {
        return clientActionType;
    }

    public void setClientActionType(ClientActionTypeEnum clientActionType) {
        this.clientActionType = clientActionType;
    }

    public PromotionTypeEnum getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionTypeEnum promotion) {
        this.promotion = promotion;
    }

    public boolean isCrossBorderTransaction() {
        return isCrossBorderTransaction;
    }

    public void setCrossBorderTransaction(boolean crossBorderTransaction) {
        isCrossBorderTransaction = crossBorderTransaction;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
