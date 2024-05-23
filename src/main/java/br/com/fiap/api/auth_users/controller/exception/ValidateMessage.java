package br.com.fiap.api.auth_users.controller.exception;

public class ValidateMessage {
  private String field;

  private String message;

  public ValidateMessage() {}

  public ValidateMessage(String field, String message) {
    this.field = field;
    this.message = message;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
