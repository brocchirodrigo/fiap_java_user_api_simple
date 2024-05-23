package br.com.fiap.api.auth_users.service;

import br.com.fiap.api.auth_users.controller.exception.ControllerNotFoundException;
import br.com.fiap.api.auth_users.dto.UserDTO;
import br.com.fiap.api.auth_users.entities.User;
import br.com.fiap.api.auth_users.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Page<UserDTO> findAll(Pageable pageable) {
    Page<User> users = userRepository.findAll(pageable);

    return users.map(this::toDTO);
  }

  public UserDTO save(UserDTO userDTO) {
    User user = toEntity(userDTO);
    user = userRepository.save(user);

    return toDTO(user);
  }

  public UserDTO update(Long id, UserDTO userDTO) {
    try {
      User user = userRepository.getReferenceById(id);

      user.setName(userDTO.name());
      user.setCPF(userDTO.cpf());
      user.setBirthDate(userDTO.birthDate());
      user.setEmail(userDTO.email());

      user = userRepository.save(user);

      return toDTO(user);

    } catch (EntityNotFoundException e) {
      throw new ControllerNotFoundException("User with id " + id + " not found");
    }
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  public UserDTO findById(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("User not found."));

    return toDTO(user);
  }

  private UserDTO toDTO(User user) {

    return new UserDTO(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getCPF(),
        user.getBirthDate()
    );

  }

  private User toEntity(UserDTO userDTO) {
    return new User(
        userDTO.id(),
        userDTO.name(),
        userDTO.email(),
        userDTO.cpf(),
        userDTO.birthDate()
    );
  }
}
