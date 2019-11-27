package models;

import javax.persistence.*;

@Entity
@Table(name = "DISTRIBUTOR", schema = "JAVAPROJECT", catalog = "")
public class DistributorEntity {
    private long distributorId;
    private Long distributorSold;
    private Double distributorRating;
    private Double distributorSalary;
    private String distributorCompany;

    @Id
    @Column(name = "DISTRIBUTOR_ID")
    public long getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(long distributorId) {
        this.distributorId = distributorId;
    }

    @Basic
    @Column(name = "DISTRIBUTOR_SOLD")
    public Long getDistributorSold() {
        return distributorSold;
    }

    public void setDistributorSold(Long distributorSold) {
        this.distributorSold = distributorSold;
    }

    @Basic
    @Column(name = "DISTRIBUTOR_RATING")
    public double getDistributorRating() {
        return distributorRating;
    }

    public void setDistributorRating(double distributorRating) {
        this.distributorRating = distributorRating;
    }

    @Basic
    @Column(name = "DISTRIBUTOR_SALARY")
    public Double getDistributorSalary() {
        return distributorSalary;
    }

    public void setDistributorSalary(Double distributorSalary) {
        this.distributorSalary = distributorSalary;
    }

    @Basic
    @Column(name = "DISTRIBUTOR_COMPANY")
    public String getDistributorCompany() {
        return distributorCompany;
    }

    public void setDistributorCompany(String distributorCompany) {
        this.distributorCompany = distributorCompany;
    }

    @OneToOne()
    @MapsId
    private UserDetailsEntity userDetailsEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistributorEntity that = (DistributorEntity) o;

        if (distributorSold != null ? !distributorSold.equals(that.distributorSold) : that.distributorSold != null)
            return false;
        if (distributorRating != null ? !distributorRating.equals(that.distributorRating) : that.distributorRating != null)
            return false;
        if (distributorSalary != null ? !distributorSalary.equals(that.distributorSalary) : that.distributorSalary != null)
            return false;
        if (distributorCompany != null ? !distributorCompany.equals(that.distributorCompany) : that.distributorCompany != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = distributorSold != null ? distributorSold.hashCode() : 0;
        result = 31 * result + (distributorRating != null ? distributorRating.hashCode() : 0);
        result = 31 * result + (distributorSalary != null ? distributorSalary.hashCode() : 0);
        result = 31 * result + (distributorCompany != null ? distributorCompany.hashCode() : 0);
        return result;
    }
}
