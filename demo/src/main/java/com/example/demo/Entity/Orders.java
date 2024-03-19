package com.example.demo.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    public Orders(long idOrder, LocalDateTime orderDate){
        this.idOrder = idOrder;
        this.orderDate = orderDate;
    }

    public Orders() {

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

    @Override
    public String toString() {
        return "Orders{" +
                "idOrder=" + idOrder +
                ", orderDate=" + orderDate +
                '}';
    }
}
