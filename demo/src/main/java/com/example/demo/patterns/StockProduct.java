package com.example.demo.patterns;

public interface StockProduct {
    void registerObserver(StockObserver observer);
    void removeObserver(StockObserver observer);
    void notifyObservers(String stockName, double price);
    
}
