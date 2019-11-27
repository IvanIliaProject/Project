package models;

import javax.persistence.*;

@Entity
@Table(name = "ADMIN", schema = "JAVAPROJECT", catalog = "")
public class AdminEntity {


    @Id
    @Column(name = "ADMIN_ID")
    private long adminId;
    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    @Column(name = "ADMIN_CREATED")
    private Long adminCreated;
    public Long getAdminCreated() {
        return adminCreated;
    }

    public void setAdminCreated(Long adminCreated) {
        this.adminCreated = adminCreated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntity that = (AdminEntity) o;

        if (adminCreated != null ? !adminCreated.equals(that.adminCreated) : that.adminCreated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return adminCreated != null ? adminCreated.hashCode() : 0;
    }
}
