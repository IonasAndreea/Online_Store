package com.example.demo.controllers;

import com.example.demo.entity.Clients;
import com.example.demo.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The controller class responsible for managing HTTP requests related to clients (users).
 * It provides endpoints for retrieving, creating, updating, and deleting client entities.
 * This class serves as the interface between the client-side applications and the client management system.
 */
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/clients")
public class UserController {
    @Autowired
    private ClientsService clientsService;

    /**
     * Retrieves a list of all clients stored in the system.
     *
     * @return A list containing all clients and an HTTP status code 200 (OK).
     */
    @GetMapping
    public List<Clients> getAllClients(){
        return clientsService.getAllClients();
    }

    /**
     * Retrieves a specific client based on its unique identifier.
     *
     * @param id The unique identifier of the client to retrieve.
     * @return A ResponseEntity containing the retrieved client and an HTTP status code 200 (OK) if found,
     * or an HTTP status code 404 (Not Found) if the client is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clients>> getClientsById(@PathVariable Long id){
        Optional<Optional<Clients>> optionalClients = Optional.ofNullable(clientsService.getClientsById(id));
        if(optionalClients.isPresent()){
            return ResponseEntity.ok(optionalClients.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new client entity within the system.
     *
     * @param client The client object to be created.
     * @return A ResponseEntity containing the created client and an HTTP status code 201 (Created).
     */
    @PostMapping("/insertClient")
    public ResponseEntity<Clients> createClient(@RequestBody Clients client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientsService.createClient(client));
    }


    /**
     * Updates an existing client entity with new information provided.
     *
     * @param id The unique identifier of the client to update.
     * @param client The updated client object containing new attribute values.
     * @return A ResponseEntity containing the updated client and an HTTP status code 200 (OK) if successful,
     * or an HTTP status code 404 (Not Found) if the client with the given ID is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Clients> updateCliente(@PathVariable Long id, @RequestBody Clients client){
        Clients updateClient = clientsService.updateClient(id, client);
        if(updateClient != null){
            return ResponseEntity.ok(updateClient);
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * Deletes a client from the system based on its unique identifier.
     *
     * @param id The unique identifier of the client to delete.
     * @return A ResponseEntity with an HTTP status code 204 (No Content) indicating successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientsService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Clients client) {
        Clients loggedInClient = clientsService.authenticate(client.getEmail(), client.getPassword());
        if (loggedInClient != null) {
            return ResponseEntity.ok(loggedInClient);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or password is incorrect");
        }
    }

}
