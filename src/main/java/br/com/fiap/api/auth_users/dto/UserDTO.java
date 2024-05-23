package br.com.fiap.api.auth_users.dto;

import java.time.LocalDate;

public record UserDTO(
    Long id,

    String name,

    String email,

    String cpf,

    LocalDate birthDate
) {
}
