package br.com.fiap.api.auth_users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserDTO(
    Long id,

    @NotBlank(message = "The name must not be blank or null.")
    String name,

    @Email(message = "Invalid e-mail.")
    String email,

    @CPF(message = "Brazilian CPF not valid.")
    String cpf,

    @PastOrPresent(message = "Invalid date.")
    LocalDate birthDate
) {
}
