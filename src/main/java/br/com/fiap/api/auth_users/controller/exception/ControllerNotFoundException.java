package br.com.fiap.api.auth_users.controller.exception;

public class ControllerNotFoundException extends RuntimeException {
  public ControllerNotFoundException(String message) {
    super(message);
  }
}
