package com.exemple.garagemanagementclientservice.repositories;

import com.exemple.garagemanagementclientservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,String> {
    Optional<Client> findClientByEmail(String email);
}
