package geny.persistence.entity;

import geny.common.enumtype.RedemptionTypeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dat on 2/22/2018.
 */

@Entity
@Table(name = "redemption")
public class Redemption extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "redemption_type")
    private RedemptionTypeEnum redemptionType;

    @Min(0)
    @Column(name = "redemption_value")
    private BigDecimal redemptionValue;

    @Min(0)
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    public RedemptionTypeEnum getRedemptionType() {
        return redemptionType;
    }

    public void setRedemptionType(RedemptionTypeEnum redemptionType) {
        this.redemptionType = redemptionType;
    }

    public BigDecimal getRedemptionValue() {
        return redemptionValue;
    }

    public void setRedemptionValue(BigDecimal redemptionValue) {
        this.redemptionValue = redemptionValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
