package com.ex.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author that-team
 *This class defines an address.
 * @param addressID - identifying number for each address, used for identifying a user's address
 * @param number - numerical part of a street address, e.g. the 123 in 123 Fake St
 * @param street - street name, e.g. the Fake in 123 Fake St
 * @param address2 - second line of address, e.g. apartment number. It can be left blank
 * @param city - city
 * @param state - state, currently limited to 2 characters, may need to make larger for worldwide deployment
 * @param country - country
 * @param zipcode - zip code, may need to change for worldwide deployment
 * @param user = Hibernate mapping for foreign key relationship to User object Primary Key*
 */
@Entity
@Table(name="\"Addresses\"", schema = "\"that-team_schema\"")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;
    private int number ;
    private String street ;
    private String address2 ;
    private String city ;
    private String state ;
    private String country ;
    private int zipcode ;

    @OneToMany(mappedBy = "addressId", cascade=CascadeType.ALL)
    private List<User> user;

    public Address(){}

    public Address(int number, String street, String address2, String city, String state, String country, int zipCode) {
        this.number = number;
        this.street = street;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipCode;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getZipcode() {
        return zipcode;
    }
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    public int getAddressId() {
        return addressId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", number=" + number +
                ", street='" + street + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipcode=" + zipcode +
                '}';
    }
}
