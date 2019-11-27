package models;

import javax.persistence.*;

@Entity
@Table(name = "USER_TYPE", schema = "JAVAPROJECT", catalog = "")
public class UserTypeEntity {
    private long userTypeId;
    private String userTypeType;

    @Id
    @Column(name = "USER_TYPE_ID")
    public long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(long userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Basic
    @Column(name = "USER_TYPE_TYPE")
    public String getUserTypeType() {
        return userTypeType;
    }

    public void setUserTypeType(String userTypeType) {
        this.userTypeType = userTypeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTypeEntity that = (UserTypeEntity) o;

        if (userTypeId != that.userTypeId) return false;
        if (userTypeType != null ? !userTypeType.equals(that.userTypeType) : that.userTypeType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userTypeId ^ (userTypeId >>> 32));
        result = 31 * result + (userTypeType != null ? userTypeType.hashCode() : 0);
        return result;
    }
}
