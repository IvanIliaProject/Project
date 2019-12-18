package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "USER_DETAILS", schema = "JAVAPROJECT", catalog = "")
public class UserDetailsEntity {


    public UserDetailsEntity(){}

    public UserDetailsEntity(String userFullname, String userUsername, String userPassword, String userPhone, String userEmail) {
        this.userFullname = userFullname;
        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="USER_DETAILS_USER_ID_SEQ", allocationSize=1)
    @Column(name = "USER_ID")
    private long userId;
    public Long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USER_FULLNAME")
    private String userFullname;
    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    @Basic
    @Column(name = "USER_USERNAME")
    private String userUsername;
    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    @Basic
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "USER_PHONE")
    private String userPhone;
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "USER_EMAIL")
    private String userEmail;
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private OrganizerEntity organizerEntity;
    public OrganizerEntity getOrganizerEntity() {
        return organizerEntity;
    }
    public void setOrganizerEntity(OrganizerEntity organizerEntity) {
        this.organizerEntity = organizerEntity;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public DistributorEntity distributorEntity;

    public DistributorEntity getDistributorEntity() {
        return distributorEntity;
    }

    public void setDistributorEntity(DistributorEntity distributorEntity) {
        this.distributorEntity = distributorEntity;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AdminEntity adminEntity;
    public AdminEntity getAdminEntity() {
        return adminEntity;
    }
    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CustomerEntity customerEntity;

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserTypeEntity.class)
    @JoinColumn(name = "USER_TYPE_ID")
    private UserTypeEntity userTypeEntity;


    public UserTypeEntity getUserTypeEntity() {
        return userTypeEntity;
    }

    public void setUserTypeEntity(UserTypeEntity userTypeEntity) {
        this.userTypeEntity = userTypeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetailsEntity that = (UserDetailsEntity) o;

        if (userId != that.userId) return false;
        if (userFullname != null ? !userFullname.equals(that.userFullname) : that.userFullname != null) return false;
        if (userUsername != null ? !userUsername.equals(that.userUsername) : that.userUsername != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (userPhone != null ? !userPhone.equals(that.userPhone) : that.userPhone != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userFullname != null ? userFullname.hashCode() : 0);
        result = 31 * result + (userUsername != null ? userUsername.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        return result;
    }
}
