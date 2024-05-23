package br.com.fiap.api.auth_users.controller;

import br.com.fiap.api.auth_users.dto.UserDTO;
import br.com.fiap.api.auth_users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  public final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Page<UserDTO>> findAll(
      @PageableDefault(size = 10, page = 0, sort = "name")Pageable pageable
      ) {
    Page<UserDTO> usersDTOS = userService.findAll(pageable);
    return ResponseEntity.ok(usersDTOS);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
    UserDTO userDTO = userService.findById(id);
    return ResponseEntity.ok(userDTO);
  }

  @PostMapping
  public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
    UserDTO savedUser = userService.save(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    UserDTO updatedUser = userService.update(id, userDTO);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
