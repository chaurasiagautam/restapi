package com.gautam.restservices.services;

import com.gautam.restservices.entities.User;
import com.gautam.restservices.exception.UserExistException;
import com.gautam.restservices.exception.UserNotFoundException;
import com.gautam.restservices.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Scope("prototype?")
public class UserService {

  @Autowired
  private UserRepository repository;

  public List<User> getAllUsers(){
    return repository.findAll();
  }
  
  public User createUser(User user) throws UserExistException {
    if(null != this.findByUserName(user.getUserName()))
      throw new UserExistException("User already exist");
    return repository.save(user);
  }

  public Optional<User> getUserById(Long id) throws UserNotFoundException {
    Optional<User> user = repository.findById(id);
    if(!user.isPresent())
      throw new UserNotFoundException("No such user found");
    return repository.findById(id);
  }

  public User updateUserById(Long id, User user) throws UserNotFoundException {
    this.getUserById(id);
    user.setId(id);
    return repository.save(user);
  }

  public void deleteUser(Long id){
    if (repository.findById(id).isPresent() ) {
      repository.deleteById(id);
    } else
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No User found to delete");
  }

  public User findByUserName(String userName){
    return repository.findByUserName(userName);
  }
}
