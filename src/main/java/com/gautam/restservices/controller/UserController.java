package com.gautam.restservices.controller;

import com.gautam.restservices.entities.User;
import com.gautam.restservices.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping(value = "/getalluser")
  public List<User> getAllUsers(){
    return service.getAllUsers();
  }

  @PostMapping(value = "/createuser")
  public User createUser(@RequestBody User user){
    return service.createUser(user);
  }

  @GetMapping(value = "/getuser/{id}")
  public Optional<User> getUserById(@PathVariable("id") Long id){
    return service.getUserById(id);
  }

  @PutMapping(value = "/updateuser/{id}")
  public User updateUserById(@PathVariable Long id, @RequestBody User user){
    return service.updateUserById(id,user);
  }

  @DeleteMapping(value = "deleteuser/{id}")
  public boolean deleteUser(@PathVariable Long id) {
    return service.deleteUser(id);
  }

  @GetMapping(value = "/getuser/username/{username}")
  public User getUserByUserName(@PathVariable("username") String userName){
    return service.findByUserName(userName);
  }
}
