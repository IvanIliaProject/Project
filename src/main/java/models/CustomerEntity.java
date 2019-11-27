package models;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER", schema = "JAVAPROJECT", catalog = "")
public class CustomerEntity {
    private long customerId;
    private String customerAddress;

    @Id
    @Column(name = "CUSTOMER_ID")
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "CUSTOMER_ADDRESS")
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @OneToOne()
    @MapsId
    private UserDetailsEntity userDetailsEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (customerAddress != null ? !customerAddress.equals(that.customerAddress) : that.customerAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return customerAddress != null ? customerAddress.hashCode() : 0;
    }
}
