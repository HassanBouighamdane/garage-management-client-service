package com.exemple.garagemanagementclientservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "clients")
public class Client {
    @Id
    private String cin;
    private String firstName;
    private String lastName;
    private String address;
    @Column(unique = true)
    private String email;
    private String phone;
}
