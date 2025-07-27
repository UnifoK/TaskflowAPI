package io.github.unifok.taskflowapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data; // Import the Data annotation

@Entity
@Data // This is the only change you need
public class User {
    @Id @GeneratedValue
    private Long id; // Fields should be private
    private String username;
    private String password;
    private String email;
}