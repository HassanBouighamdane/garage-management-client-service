package com.exemple.garagemanagementclientservice.services;

import com.exemple.garagemanagementclientservice.entities.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(String id);
    Client createClient(Client client);
    Client updateClient(String id,Client client);
    void deleteClient(String id);
    Client getClientByEmail(String email);

}
