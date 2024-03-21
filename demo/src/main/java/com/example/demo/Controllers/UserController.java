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

    @GetMapping
    public List<Clients> getAllClients(){
        return clientsService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clients>> getClientsById(@PathVariable Long id){
        Optional<Optional<Clients>> optionalClients = Optional.ofNullable(clientsService.getClientsById(id));
        if(optionalClients.isPresent()){
            return ResponseEntity.ok(optionalClients.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/insertClient")
    public ResponseEntity<Clients> createClient(@RequestBody Clients client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientsService.createClient(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clients> updateCliente(@PathVariable Long id, @RequestBody Clients client){
        Clients updateClient = clientsService.updateClient(id, client);
        if(updateClient != null){
            return ResponseEntity.ok(updateClient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientsService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
