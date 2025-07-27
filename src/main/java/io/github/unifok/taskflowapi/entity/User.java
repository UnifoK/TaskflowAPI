package io.github.unifok.taskflowapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id @GeneratedValue
    String id;
    String username;
    String password;
    String email;
}
