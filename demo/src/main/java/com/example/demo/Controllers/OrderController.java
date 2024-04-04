package com.example.demo.Controllers;

import com.example.demo.Entity.Clients;
import com.example.demo.Entity.Orders;
import com.example.demo.Entity.Products;
import com.example.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/oredrs")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     *
     * @return The list with all the orders
     */
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders(){
        List<Orders> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     *
     * @param id the id of the order we want to find
     * @return the order we found
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
     *
     * @param order  representing the order to be created.
     * @return Representing the created order.
     */
    @PostMapping("/insertOrder")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order){
        return  ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));

    }

    /**
     *
     * @param id The ID of the order to update.
     * @param order Representing the updated order data.
     * @return The updated order.
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
     *
     * @param id The ID of the order to delete.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
