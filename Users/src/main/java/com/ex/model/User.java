package com.ex.model;


import javax.persistence.*;

/**
 * @author that-team
 * This class defines a user.
 * The user has:
 * @param firstName - user's first name
 * @param lastName - user's last name
 * @param phoneNumber - used with carrier to generate push notifications
 * @param carrier - used with phoneNumber to generate push notifications
 * @param email - used for login and email notifications
 * @param password - used for logging in
 * @param address - address, used for finding items near user's home
 * @param experiencePoints - amount of experience the user has gained from placing and retrieving items, used for earning badges
 * @param inactiveUser - describes the activation status of a user
 */

@Entity
@Table(name="\"Users\"", schema = "\"that-team_schema\"")
public class User implements Cloneable{
    private String firstname ;
    private String lastname ;
    private String phone ;

    @ManyToOne
    @JoinColumn(name = "phone_carrier_id")
    private PhoneCarrier phoneCarrierId;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email")
    private String email ;
    @Column(name = "password")
    private String password ;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address addressId ;

    @Column(name = "experience_points")
    private int experiencePoints;

    @Column(name = "inactive_user")
    private boolean inactiveUser;

    public User(){
        this.firstname = "firstName";
        this.lastname = "lastName";
        this.phone = "phoneNumber";
        this.phoneCarrierId = null;
        this.email = "email";
        this.password = "password";
        this.addressId  = null;
        this.experiencePoints = 0;
        this.inactiveUser = true;
    }

    public User(String firstName, String lastName, String phoneNumber, PhoneCarrier carrier, String email,
                String password, Address address, int experiencePoints, boolean inactiveUser) {

        this.firstname = firstName;
        this.lastname = lastName;
        this.phone = phoneNumber;
        this.phoneCarrierId = carrier;
        this.email = email;
        this.password = password;
        this.addressId  = address;
        this.experiencePoints = experiencePoints;
        this.inactiveUser = inactiveUser;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public PhoneCarrier getPhonecarrierid() {
        return phoneCarrierId;
    }
    public void setPhonecarrierid(PhoneCarrier phonecarrierid) {
        this.phoneCarrierId = phonecarrierid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Address getAddressid() {
        return addressId ;
    }
    public void setAddressid(Address addressid) {
        this.addressId = addressid;
    }
    public int getExperiencePoints() {
        return experiencePoints;
    }
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isInactiveUser() {
        return inactiveUser;
    }
    public void setInactiveUser(boolean inactiveUser) {
        this.inactiveUser = inactiveUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", phoneCarrierId=" + phoneCarrierId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", addressId=" + addressId +
                ", experiencePoints=" + experiencePoints +
                ", inactiveUser=" + inactiveUser +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
