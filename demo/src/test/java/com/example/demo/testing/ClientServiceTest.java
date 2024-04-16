package com.example.demo.testing;

import com.example.demo.entity.Clients;
import com.example.demo.entity.Products;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.services.ClientsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ClientServiceTest {
    private ClientsService clientsService;

    @Mock
    private ClientRepository clientRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.clientsService = new ClientsService(clientRepositoryMock);

    }

    @Test
    public void testCreate() {
        Clients client = new Clients(10, "John", "john@example.com", "password123", "123 Street", false);
        this.clientsService.createClient(client);
        Mockito.verify(this.clientRepositoryMock).save(client);
    }

    @Test
    public void TestGetAll(){
        Clients client1 = new Clients(30, "ElenaPop", "ema@pop.com", "1639*pop", "Cluj", false);
        Clients client2 = new Clients(33, "Mara","mara@here.com", "mar996523", "Cluj", false);
        List<Clients> clientsList = List.of(client1, client2);
        when(this.clientRepositoryMock.findAll()).thenReturn(clientsList);
        List<Clients> res = this.clientsService.getAllClients();
        Mockito.verify(this.clientRepositoryMock).findAll();
        assertEquals(clientsList, res);

    }

    @Test
    public void testGetById() {
        Clients client = new Clients(33, "Mara","mara@here.com", "mar996523", "Cluj", false);
        when(this.clientRepositoryMock.findById(client.getId())).thenReturn(Optional.of(client));
        Optional<Clients> res = this.clientsService.getClientsById(client.getId());
        Mockito.verify(this.clientRepositoryMock).findById(client.getId());
        assertTrue(res.isPresent());
        assertEquals(client, res.get());
    }

    @Test
    public void testDelete() {
        long clientId = 10;
        doNothing().when(this.clientRepositoryMock).deleteById(clientId);
        this.clientsService.deleteClient(clientId);
        Mockito.verify(this.clientRepositoryMock).deleteById(clientId);
    }


}
