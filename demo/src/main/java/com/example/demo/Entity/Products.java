package com.example.demo.Entity;

import jakarta.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "products")

public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod")
    private long idProduct;
    @Column(name = "name_prod")
    private String nameProduct;
    private String description;
    private double price;
    private int stock;

    public Products(long idProduct, String nameProduct, String description, double price, int stock){
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
    public Products() {

    }

    public long getIdProduct() {
        return idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Products{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }
}
