package com.example.demo.services;

import com.example.demo.entity.Clients;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The service class provides functionality for managing clients within the system.
 * It encapsulates operations such as retrieval, creation, update, and deletion of client entities.
 * This class serves as a bridge between the controller layer and the data access layer,
 * ensuring proper handling and manipulation of client data.
 */
@Service
public class ClientsService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    /**
     * Retrieves a list of all clients stored in the system.
     *
     * @return A list containing all clients available in the system.
     */
    public List<Clients> getAllClients(){
        return clientRepository.findAll();
    }

    /**
     * Retrieves a specific client based on its unique identifier.
     *
     * @param id The unique identifier of the client to retrieve.
     * @return An Optional containing the retrieved client, if found; otherwise, an empty Optional.
     */
    public Optional<Clients> getClientsById(long id){
        return clientRepository.findById(id);
    }

    /**
     * Creates a new client entity within the system.
     *
     * @param client The client object to be created.
     * @return The newly created client entity after successful persistence.
     */
    public Clients createClient(Clients client){
        return clientRepository.save(client);
    }

    /**
     * Deletes a client from the system based on its unique identifier.
     *
     * @param id The unique identifier of the client to delete.
     */
    public void deleteClient(long id){
        clientRepository.deleteById(id);
    }


    /**
     * Updates an existing client entity with new data provided.
     *
     * @param id The unique identifier of the client to update.
     * @param client The updated client object containing new attribute values.
     * @return The updated client entity after successful persistence,
     * or null if no client with the provided ID exists.
     * or null if no client with the provided ID exists.
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
