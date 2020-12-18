package com.gautam.restservices.exception;

public class UserNotFoundException extends Exception {

  private static final long servialVersionUID = 1L;

  public UserNotFoundException(String message) {
    super(message);
  }
}
