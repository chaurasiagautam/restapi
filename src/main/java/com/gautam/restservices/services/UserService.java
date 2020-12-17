package com.gautam.restservices.services;

import com.gautam.restservices.entities.User;
import com.gautam.restservices.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public List<User> getAllUsers(){
    return repository.findAll();
  }
  
  public User createUser(User user){
    return repository.save(user);
  }

  public Optional<User> getUserById(Long id){
    return repository.findById(id);
  }

  public User updateUserById(Long id, User user){
    user.setId(id);
    return repository.save(user);
  }

  public boolean deleteUser(Long id){
    if (repository.findById(id).isPresent() ) {
      repository.deleteById(id);
      return true;
    } else
      return false;
  }

  public User findByUserName(String userName){
    return repository.findByUserName(userName);
  }
}
