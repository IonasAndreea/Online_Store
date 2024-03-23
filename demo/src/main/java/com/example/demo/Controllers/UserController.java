package com.example.demo.Controllers;

import com.example.demo.Entity.Clients;
import com.example.demo.Services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class UserController {
    @Autowired
    private ClientsService clientsService;

    /**
     *
     * @return the list with all the clients
     */
    @GetMapping
    public List<Clients> getAllClients(){
        return clientsService.getAllClients();
    }

    /**
     *
     * @param id the id of the client we want to find
     * @return the client we found
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
     *
     * @param client  Representing the client to be created.
     * @return Representing the created client.
     */
    @PostMapping("/insertClient")
    public ResponseEntity<Clients> createClient(@RequestBody Clients client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientsService.createClient(client));
    }


    /**
     *
     * @param id The ID of the client to update.
     * @param client The client representing the updated client data.
     * @return The updated product.
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
     *
     * @param id The ID of the client to delete.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientsService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
