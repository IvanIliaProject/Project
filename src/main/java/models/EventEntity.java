package models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "EVENT", schema = "JAVAPROJECT", catalog = "")
public class EventEntity {






    @Id
    @Column(name = "EVENT_ID")
    private long eventId;
    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "EVENT_NAME")
    private String eventName;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Basic
    @Column(name = "EVENT_PRICE")
    private Double eventPrice;
    public Double getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(Double eventPrice) {
        this.eventPrice = eventPrice;
    }

    @Basic
    @Column(name = "EVENT_PLACE")
    private String eventPlace;
    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    @Basic
    @Column(name = "EVENT_DATE")
    private Date eventDate;
    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Basic
    @Column(name = "EVENT_SEATS")

    private Long eventSeats;
    public Long getEventSeats() {
        return eventSeats;
    }

    public void setEventSeats(Long eventSeats) {
        this.eventSeats = eventSeats;
    }

    @Basic
    @Column(name = "EVENT_QUANTITY")
    private Long eventQuantity;
    public Long getEventQuantity() {
        return eventQuantity;
    }

    public void setEventQuantity(Long eventQuantity) {
        this.eventQuantity = eventQuantity;
    }


    @Basic
    @Column(name = "EVENT_TIME")
    private String eventTime;

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = OrganizerEntity.class)
    @JoinColumn(name = "ORGANIZER_ID")
    private OrganizerEntity organizerEntity;

    public OrganizerEntity getOrganizerEntity() {
        return organizerEntity;
    }

    public void setOrganizerEntity(OrganizerEntity organizerEntity) {
        this.organizerEntity = organizerEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEntity that = (EventEntity) o;

        if (eventId != that.eventId) return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;
        if (eventPrice != null ? !eventPrice.equals(that.eventPrice) : that.eventPrice != null) return false;
        if (eventPlace != null ? !eventPlace.equals(that.eventPlace) : that.eventPlace != null) return false;
        if (eventDate != null ? !eventDate.equals(that.eventDate) : that.eventDate != null) return false;
        if (eventSeats != null ? !eventSeats.equals(that.eventSeats) : that.eventSeats != null) return false;
        if (eventQuantity != null ? !eventQuantity.equals(that.eventQuantity) : that.eventQuantity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (eventId ^ (eventId >>> 32));
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (eventPrice != null ? eventPrice.hashCode() : 0);
        result = 31 * result + (eventPlace != null ? eventPlace.hashCode() : 0);
        result = 31 * result + (eventDate != null ? eventDate.hashCode() : 0);
        result = 31 * result + (eventSeats != null ? eventSeats.hashCode() : 0);
        result = 31 * result + (eventQuantity != null ? eventQuantity.hashCode() : 0);
        return result;
    }
}
