package geny.persistence.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "loyalty")
public class Loyalty extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "phone", nullable = false, unique = true)
    private String phoneNumber;

    @NotNull
    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Min(0)
    @Column(name = "point")
    private int point;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "loyalty_id")
    private List<Redemption> redemptionList;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    private List<Redemption> getRedemptionListInternal() {
        return redemptionList == null ? Collections.emptyList() : redemptionList;
    }

    private void setRedemptionListInternal(List<Redemption> redemptionList) {
        this.redemptionList = redemptionList;
    }

    public void addRedemption(Redemption redemption) {
        getRedemptionListInternal().add(redemption);
    }

    public List<Redemption> getRedemptionList() {
        return getRedemptionListInternal();
    }

    public void setRedemptionList(List<Redemption> redemptionList) {
        this.redemptionList = redemptionList;
    }
}
