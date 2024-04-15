package com.example.demo.entity;
import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private long idOrder;
    @Column(name = "order_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime orderDate;
   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "product_id")
   @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
   @JoinTable(name = "orders_product",
           joinColumns = {
                   @JoinColumn(name = "order_id",
                           referencedColumnName = "id_order")},
           inverseJoinColumns = {
                   @JoinColumn(name = "Product_id",
                           referencedColumnName = "id_prod")})
   private List<Products> product;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Clients client;
    private double price;

    public Orders(long idOrder, LocalDateTime orderDate, List <Products> product, Clients client, double price){
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.product = product;
        this.client = client;
        this.price = price;
    }

    public Orders() {

    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public List<Products> getProduct() {
        return product;
    }

    public void setProduct(List<Products> product) {
        this.product = product;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "idOrder=" + idOrder +
                ", orderDate=" + orderDate +
                ", product=" + product +
                ", price=" + price +
                '}';
    }
}
