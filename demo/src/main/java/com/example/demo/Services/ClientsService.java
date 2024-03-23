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


    /**
     *
     * @return a list of all clients.
     */
    public List<Clients> getAllClients(){
        return clientRepository.findAll();
    }

    /**
     *
     * @param id is the id of the client to retrieve
     * @return Representing the retrieved client
     */
    public Optional<Clients> getClientsById(long id){
        return clientRepository.findById(id);
    }

    /**
     *
     * @param client is the client that we want to create
     * @return the actual created client
     */

    public Clients createClient(Clients client){
        return clientRepository.save(client);
    }

    /**
     *
     * @param id the ID of the client to delete
     */
    public void deleteClient(long id){
        clientRepository.deleteById(id);
    }


    /**
     *
     * @param id the id of the client we need to update
     * @param client representing the updated client data
     * @return Representing the updated client
     */
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
