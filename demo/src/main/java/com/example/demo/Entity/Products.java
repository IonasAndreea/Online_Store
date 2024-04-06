package com.example.demo.Entity;

import com.example.demo.patterns.StockObserver;
import com.example.demo.patterns.StockProduct;
import jakarta.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")

public class Products implements StockProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod")
    private long idProduct;
    @Column(name = "name_prod")
    private String nameProduct;
    private String description;
    private double price;
    private int stock;
    @Transient
    private List <StockObserver> observers = new ArrayList<>();
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

    @Override
    public void registerObserver(StockObserver observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(StockObserver observer) {
        observers.remove(observer);

    }

    @Override
    public void notifyObservers(String stockName, int stock) {
        for(StockObserver observer : observers){
            observer.update(stockName, stock);
        }

    }

    public void setStock(String stockName, int stock){
        notifyObservers(stockName, stock);
    }
}
