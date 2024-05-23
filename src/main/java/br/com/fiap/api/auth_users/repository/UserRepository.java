package br.com.fiap.api.auth_users.repository;

import br.com.fiap.api.auth_users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
