package com.exemple.garagemanagementclientservice.services.implementation;

import com.exemple.garagemanagementclientservice.entities.Client;
import com.exemple.garagemanagementclientservice.exceptions.BusinessException;
import com.exemple.garagemanagementclientservice.repositories.ClientRepository;
import com.exemple.garagemanagementclientservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(String id)  {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()){
            throw new BusinessException("Client not found");
        }
        return client.get();
    }

    @Override
    public Client getClientByEmail(String email){
        Optional<Client> client = clientRepository.findClientByEmail(email);
        if(client.isEmpty()){
            throw new BusinessException("Client Not Found");
        }
        return client.get();
    }

    @Override
    public Client createClient(Client client){
        Optional<Client> existingClient=clientRepository.findById(client.getCin());
        if(existingClient.isPresent()) {
            throw new BusinessException("Client already exists");
        }
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(String id,Client client) {
        Optional<Client> existingClient=clientRepository.findById(id);
        if(existingClient.isEmpty()) {
            throw new BusinessException("Client not found");
        }
        Client updatedClient = existingClient.get();

        updatedClient.setEmail(client.getEmail());
        updatedClient.setFirstName(client.getFirstName());
        updatedClient.setLastName(client.getLastName());
        updatedClient.setPhone(client.getPhone());
        updatedClient.setAddress(client.getAddress());

        return clientRepository.save(updatedClient);
    }

    @Override
    public void deleteClient(String id) {
        Optional<Client> client=clientRepository.findById(id);
        if(client.isEmpty()) {
            throw new BusinessException("Client not found");
        }
        clientRepository.delete(client.get());
    }

}
