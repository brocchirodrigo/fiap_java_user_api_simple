package br.com.fiap.api.auth_users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserDTO(
    Long id,

    @NotBlank(message = "The name must not be blank or null.")
    String name,

    @Email(message = "Invalid e-mail.")
    String email,

    String cpf,

    LocalDate birthDate
) {
}
