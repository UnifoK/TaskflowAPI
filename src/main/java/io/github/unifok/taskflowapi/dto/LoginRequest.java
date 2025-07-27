package io.github.unifok.taskflowapi.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}