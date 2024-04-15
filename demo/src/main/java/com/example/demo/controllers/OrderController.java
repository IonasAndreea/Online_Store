package com.example.demo.controllers;

import com.example.demo.entity.Orders;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The controller class responsible for handling HTTP requests related to orders.
 * This class provides endpoints for retrieving, creating, updating, and deleting orders.
 * It serves as the interface between the client-side applications and the order management system.
 */
@RestController
@RequestMapping("/oredrs")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * Retrieves a list of all orders stored in the system.
     *
     * @return A ResponseEntity containing a list of all orders and an HTTP status code 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders(){
        List<Orders> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves a specific order based on its unique identifier.
     *
     * @param id The unique identifier of the order to retrieve.
     * @return A ResponseEntity containing the retrieved order and an HTTP status code 200 (OK) if found,
     * or an HTTP status code 404 (Not Found) if the order is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id){
        Optional<Orders> optionalOrder  = orderService.getOrderById(id);
        if(optionalOrder.isPresent()){
            return ResponseEntity.ok(optionalOrder.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new order entity within the system.
     *
     * @param order The order object to be created.
     * @return A ResponseEntity containing the created order and an HTTP status code 201 (Created).
     */
    @PostMapping("/insertOrder")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order){
        return  ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));

    }

    /**
     * Updates an existing order entity with new information provided.
     *
     * @param id    The unique identifier of the order to update.
     * @param order The updated order object containing new attribute values.
     * @return A ResponseEntity containing the updated order and an HTTP status code 200 (OK) if successful,
     * or an HTTP status code 404 (Not Found) if the order with the given ID is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders order){
        Orders updateOrder = orderService.updateOrder(id, order);
        if(updateOrder != null){
            return ResponseEntity.ok(updateOrder);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes an order from the system based on its unique identifier.
     *
     * @param id The unique identifier of the order to delete.
     * @return A ResponseEntity with an HTTP status code 204 (No Content) indicating successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
