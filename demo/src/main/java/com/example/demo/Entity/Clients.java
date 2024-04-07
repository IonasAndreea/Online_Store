package com.example.demo.Entity;

import com.example.demo.patterns.StockObserver;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clients")

public class Clients implements StockObserver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userNane;
    private String email;
    private String password;
    private String address;
    private Boolean isAdmin;


    public Clients(long id, String userName, String email, String password, String address, Boolean isAdmin){
        this.id = id;
        this.userNane = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.isAdmin = isAdmin;

    }

    public Clients() {

    }

    public long getId() {
        return id;
    }

    public String getUserNane() {
        return userNane;
    }

    public void setUserNane(String userNane) {
        this.userNane = userNane;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", userNane='" + userNane + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    /**
     *
     * @param productName the name of the product we need to update
     * @param stock representing the new stock of the product
     * @return Representing the updated product
     */
    @Override
    public void update(String productName, int stock) {
        System.out.println(id +" " +  userNane + " recived an update for " + productName + " " + stock);
    }
}
