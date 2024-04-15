package com.example.demo.testing;

import com.example.demo.entity.Clients;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.services.ClientsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
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

}
