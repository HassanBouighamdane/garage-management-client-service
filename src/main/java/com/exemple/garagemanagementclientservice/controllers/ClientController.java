package com.exemple.garagemanagementclientservice.controllers;

import com.exemple.garagemanagementclientservice.constants.Paths;
import com.exemple.garagemanagementclientservice.entities.Client;
import com.exemple.garagemanagementclientservice.exceptions.BusinessException;
import com.exemple.garagemanagementclientservice.repositories.ClientRepository;
import com.exemple.garagemanagementclientservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.BASE_API)
public class ClientController {
    private final ClientService clientService;;

    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;

    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> listClient(){
        List<Client> clients=clientService.getAllClients();
        return new ResponseEntity<>(clients,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id){
        try{
            return new ResponseEntity<>(clientService.getClientById(id),HttpStatus.OK);
        }catch(BusinessException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        try{
            return new ResponseEntity<>(clientService.createClient(client),HttpStatus.CREATED);
        }catch(BusinessException ex){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable String id,@RequestBody Client client){
        try {
            return new ResponseEntity<>(clientService.updateClient(id,client),HttpStatus.OK);
        }catch(BusinessException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        try {
            clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BusinessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
