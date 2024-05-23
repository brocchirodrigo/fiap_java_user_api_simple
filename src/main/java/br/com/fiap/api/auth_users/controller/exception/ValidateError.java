package br.com.fiap.api.auth_users.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidateError extends StandardError {
  private List<ValidateMessage> messages = new ArrayList<>();

  public List<ValidateMessage> getMessages() {
    return messages;
  }

  public void addMessages (String field, String message) {
    messages.add(new ValidateMessage(field, message));
  }
}
