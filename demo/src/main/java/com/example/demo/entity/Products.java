package com.example.demo.entity;

import com.example.demo.patterns.StockObserver;
import com.example.demo.patterns.StockProduct;
import jakarta.persistence.*;

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

    /**
     *
     * @param observer add a new observer for this product
     */
    @Override
    public void registerObserver(StockObserver observer) {
        observers.add(observer);

    }

    /**
     *
     * @param observer remove an observer for this product
     */
    @Override
    public void removeObserver(StockObserver observer) {
        observers.remove(observer);

    }

    /**
     *
     * @param stockName the name of the product
     * @param price the new value of the stock
     */
    @Override
    public void notifyObservers(String stockName, double price) {
        for(StockObserver observer : observers){
            observer.update(stockName, price);
        }

    }

    public void setPrice(String stockName, double price){
        notifyObservers(stockName, price);
    }
}
