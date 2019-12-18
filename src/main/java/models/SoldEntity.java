package models;

import org.junit.Test;

import javax.persistence.*;

@Entity
@Table(name = "SOLD", schema = "JAVAPROJECT", catalog = "")
public class SoldEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "SOLD_SOLD_ID_SEQ",allocationSize = 1)
    @Column(name = "SOLD_ID")

    private long soldId;

    public long getSoldId() {
        return soldId;
    }

    public void setSoldId(long soldId) {
        this.soldId = soldId;
    }

    @Basic
    @Column(name = "SOLD")
    private Integer sold;

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }
    @Basic
    @Column(name = "QUANTITY")
    public Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DistributorEntity.class)
    @JoinColumn(name = "DISTRIBUTOR_ID")
    public DistributorEntity distributorEntity;

    public DistributorEntity getDistributorEntity() {
        return distributorEntity;
    }

    public void setDistributorEntity(DistributorEntity distributorEntity) {
        this.distributorEntity = distributorEntity;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = EventEntity.class)
    @JoinColumn(name = "EVENT_ID")
    public EventEntity eventEntity;

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }
}