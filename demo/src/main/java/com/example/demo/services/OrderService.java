package com.example.demo.services;

import com.example.demo.entity.Orders;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The service class responsible for managing orders within the system.
 * It provides methods for retrieving, creating, updating, and deleting orders.
 * This class acts as an intermediary between the controller layer and the data access layer,
 * ensuring proper handling and manipulation of order data.
 */
@Service
public class OrderService {
    private OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository){this.orderRepository = orderRepository;}

    /**
     * Retrieves a list of all orders stored in the system.
     *
     * @return A list containing all orders available in the system.
     */
    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    /**
     * Retrieves a specific order based on its unique identifier.
     *
     * @param id The unique identifier of the order to retrieve.
     * @return An Optional containing the retrieved order, if found; otherwise, an empty Optional.
     */
    public Optional <Orders> getOrderById(long id){
        return orderRepository.findById(id);
    }

    /**
     * Creates a new order entity within the system.
     *
     * @param order The order object to be created.
     * @return The newly created order entity after successful persistence.
     */
    public Orders createOrder(Orders order){
        return orderRepository.save(order);
    }


    /**
     * Deletes an order from the system based on its unique identifier.
     *
     * @param id The unique identifier of the order to delete.
     */
    public void deleteOrder(long id){
        //if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        //}
    }


    /**
     * Updates an existing order entity with new information provided.
     *
     * @param id The unique identifier of the order to update.
     * @param order The updated order object containing new attribute values.
     * @return The updated order entity after successful persistence,
     * or null if no order with the provided ID exists.
     */
    public Orders updateOrder(long id, Orders order) {
        Optional<Orders> optionalExistingOredrs = orderRepository.findById(id);
        if (optionalExistingOredrs.isPresent()) {
            Orders existingOredr = optionalExistingOredrs.get();
            existingOredr.setOrderDate(order.getOrderDate());
            existingOredr.setIdOrder(order.getIdOrder());
            existingOredr.setProduct(order.getProduct());
            existingOredr.setClient(order.getClient());
            existingOredr.setPrice(order.getPrice());
            return orderRepository.save(existingOredr);
        } else {
            return null;
        }
    }

}
