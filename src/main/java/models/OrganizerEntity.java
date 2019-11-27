package models;

import javax.persistence.*;

@Entity
@Table(name = "ORGANIZER", schema = "JAVAPROJECT", catalog = "")
public class OrganizerEntity {

    public OrganizerEntity(long organizerId, Double organizerSalary, String organizerCompany) {
        this.organizerId = organizerId;
        this.organizerSalary = organizerSalary;
        this.organizerCompany = organizerCompany;
    }
    public OrganizerEntity (){}
    @Id
    @Column(name = "ORGANIZER_ID")
    private long organizerId;
    public long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(long organizerId) {
        this.organizerId = organizerId;
    }

    @Column(name = "ORGANIZER_SALARY")
    private Double organizerSalary;
    public Double getOrganizerSalary() {
        return organizerSalary;
    }

    public void setOrganizerSalary(Double organizerSalary) {
        this.organizerSalary = organizerSalary;
    }


    @Column(name = "ORGANIZER_COMPANY")
    private String organizerCompany;
    public String getOrganizerCompany() {
        return organizerCompany;
    }

    public void setOrganizerCompany(String organizerCompany) {
        this.organizerCompany = organizerCompany;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizerEntity that = (OrganizerEntity) o;

        if (organizerSalary != null ? !organizerSalary.equals(that.organizerSalary) : that.organizerSalary != null)
            return false;
        if (organizerCompany != null ? !organizerCompany.equals(that.organizerCompany) : that.organizerCompany != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizerSalary != null ? organizerSalary.hashCode() : 0;
        result = 31 * result + (organizerCompany != null ? organizerCompany.hashCode() : 0);
        return result;
    }
}
