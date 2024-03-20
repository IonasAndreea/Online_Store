package com.example.demo.Services;

import com.example.demo.Entity.Clients;
import com.example.demo.Entity.Orders;
import com.example.demo.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Clients> getAllClients(){
        return clientRepository.findAll();
    }

    public Optional<Clients> getClientsById(long id){
        return clientRepository.findById(id);
    }

    public Clients createClient(Clients client){
        return clientRepository.save(client);
    }

    public void deleteClient(long id){
        clientRepository.deleteById(id);
    }

    public Clients updateClient(long id, Clients client) {
        Optional<Clients> optionalExistingClients = clientRepository.findById(id);
        if (optionalExistingClients.isPresent()) {
            Clients existingClient = optionalExistingClients.get();
            existingClient.setId(client.getId());
            existingClient.setAddress(client.getAddress());
            existingClient.setEmail(client.getEmail());
            existingClient.setPassword(client.getPassword());
            existingClient.setUserNane(client.getUserNane());
            existingClient.setAdmin(client.getAdmin());
            return clientRepository.save(existingClient);
        } else {
            return null;
        }
    }

}
