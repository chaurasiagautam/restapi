package com.gautam.restservices.exception;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExpHandler extends ResponseEntityExceptionHandler {

  /**
   * This type of return generally applies with @ControllerAdvice,
   * return new ResponseEntity<>(expDetails,HttpStatus.NOT_FOUND);
   * For @RestControllerAdvice
   */

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    CustomExpDetails expDetails = new CustomExpDetails(new Date(), "Excdeption in CEH",
        ex.getMessage());
    return new ResponseEntity<>(expDetails, HttpStatus.BAD_GATEWAY);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    CustomExpDetails expDetails =
        new CustomExpDetails(new Date(), "Exception in CEH | Method not supported",
            ex.getMessage());
    return new ResponseEntity<>(expDetails, HttpStatus.METHOD_NOT_ALLOWED);
  }


  @ExceptionHandler(ConstraintViolationException.class)
  public final ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException ex, WebRequest request) {
    CustomExpDetails expDetails = new CustomExpDetails(new Date(), ex.getMessage(),
        request.getDescription(true));
    return new ResponseEntity<>(expDetails, HttpStatus.NOT_FOUND);
  }
}
