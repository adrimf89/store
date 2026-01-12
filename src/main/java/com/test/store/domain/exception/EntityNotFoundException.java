package com.test.store.domain.exception;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String message) {
    super(message);
  }

}
