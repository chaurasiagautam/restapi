package com.gautam.restservices.controller;

import com.gautam.restservices.entities.User;
import com.gautam.restservices.exception.UserExistException;
import com.gautam.restservices.exception.UserNameNotFoundException;
import com.gautam.restservices.exception.UserNotFoundException;
import com.gautam.restservices.services.UserService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Validated
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping(value = "/getalluser")
  public List<User> getAllUsers() {
    return service.getAllUsers();
  }

  @PostMapping(value = "/createuser")
  public ResponseEntity<Void> createUser(@Valid @RequestBody User user,
                                         UriComponentsBuilder builder) {
    try {
      User newUsr = service.createUser(user);
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(builder.path("/getuser/{id}").buildAndExpand(user.getId()).toUri());
      return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    } catch (UserExistException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
  }

  @GetMapping(value = "/getuser/{id}")
  public Optional<User> getUserById(@PathVariable("id") @Min(2) Long id) {
    try {
      return service.getUserById(id);
    } catch (UserNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }
  }

  @PutMapping(value = "/updateuser/{id}")
  public User updateUserById(@PathVariable Long id, @RequestBody User user) {
    try {
      return service.updateUserById(id, user);
    } catch (UserNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
  }

  @DeleteMapping(value = "deleteuser/{id}")
  public void deleteUser(@PathVariable Long id) {
    service.deleteUser(id);
  }

  @GetMapping(value = "/getuser/username/{username}")
  public User getUserByUserName(@PathVariable("username") String userName)
      throws UserNameNotFoundException {
    User user = service.findByUserName(userName);
    if (null == user) {
      throw new UserNameNotFoundException("User with username: " + userName + " not found !");
    }
    return user;
  }
}
