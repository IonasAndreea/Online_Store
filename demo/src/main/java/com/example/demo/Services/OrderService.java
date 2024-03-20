package com.example.demo.Services;

import com.example.demo.Entity.Orders;
import com.example.demo.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository){this.orderRepository = orderRepository;}

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional <Orders> getOrderById(long id){
        return orderRepository.findById(id);
    }

    public Orders createOrder(Orders order){
        return orderRepository.save(order);
    }

    public void deleteOrder(long id){
        if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        }
    }

    public Orders updateOrder(long id, Orders order) {
        Optional<Orders> optionalExistingOredrs = orderRepository.findById(id);
        if (optionalExistingOredrs.isPresent()) {
            Orders existingOredr = optionalExistingOredrs.get();
            existingOredr.setOrderDate(order.getOrderDate());
            existingOredr.setIdOrder(order.getIdOrder());
            return orderRepository.save(existingOredr);
        } else {
            return null;
        }
    }

}
