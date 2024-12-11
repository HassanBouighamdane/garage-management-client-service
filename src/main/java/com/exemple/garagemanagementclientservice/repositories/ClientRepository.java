package com.exemple.garagemanagementclientservice.repositories;

import com.exemple.garagemanagementclientservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,String> {
}
