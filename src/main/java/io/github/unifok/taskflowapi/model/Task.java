package io.github.unifok.taskflowapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Task {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;
    private String title;
    private String description;
    private boolean completed;
}
