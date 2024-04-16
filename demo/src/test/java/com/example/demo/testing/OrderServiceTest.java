package com.example.demo.testing;

import com.example.demo.entity.Clients;
import com.example.demo.entity.Orders;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    private OrderService oredrService;

    @Mock
    private OrderRepository orderRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.oredrService = new OrderService(orderRepositoryMock);

    }

    @Test
    public void testCreate() {
        Orders order = new Orders();
        this.oredrService.createOrder(order);
        Mockito.verify(this.orderRepositoryMock).save(order);
    }

    @Test
    public void TestGetAll(){
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        List<Orders> ordersList = List.of(order1, order2);
        when(this.orderRepositoryMock.findAll()).thenReturn(ordersList);
        List<Orders> res = this.oredrService.getAllOrders();
        Mockito.verify(this.orderRepositoryMock).findAll();
        assertEquals(ordersList, res);

    }

    @Test
    public void testGetById() {
        long id =0;
        Orders order = new Orders();
        order.setIdOrder(id);
        when(this.orderRepositoryMock.findById(order.getIdOrder())).thenReturn(Optional.of(order));
        Optional<Orders> res = this.oredrService.getOrderById(order.getIdOrder());
        Mockito.verify(this.orderRepositoryMock).findById(order.getIdOrder());
        assertTrue(res.isPresent());
        assertEquals(order, res.get());
    }

    @Test
    public void testDelete() {
        long orderId = 10;
        doNothing().when(this.orderRepositoryMock).deleteById(orderId);
        this.oredrService.deleteOrder(orderId);
        Mockito.verify(this.orderRepositoryMock).deleteById(orderId);
    }


}
