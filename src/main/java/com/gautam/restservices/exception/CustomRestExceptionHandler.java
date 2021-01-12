package com.gautam.restservices.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestExceptionHandler {

  private static final String EXP_MSG = "Exception from Rest Handler";

  @ExceptionHandler(UserNameNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public final CustomExpDetails handleUserNameNotFound(UserNameNotFoundException ex) {
    return new CustomExpDetails(new Date(), "from Rest controller exception handler",
        ex.getMessage());
    /**
     * This type of return generally applies with @ControllerAdvice,
     * return new ResponseEntity<>(expDetails,HttpStatus.NOT_FOUND);
     * For @RestControllerAdvice we do like below.
     */
  }

  @ExceptionHandler(OrderAlreadyExistException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public final CustomExpDetails handleOrderAlreadyExist(OrderAlreadyExistException ex){
    return new CustomExpDetails(new Date(), EXP_MSG,
        ex.getMessage());
  }

  @ExceptionHandler(OrderNotFoundExcpetion.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public final CustomExpDetails handleOrderNotFound(OrderNotFoundExcpetion ex){
    return new CustomExpDetails(new Date(),EXP_MSG,ex.getMessage());
  }
}
