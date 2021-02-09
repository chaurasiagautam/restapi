package com.gautam.restservices.services;

import com.gautam.restservices.entities.Order;
import com.gautam.restservices.entities.User;
import com.gautam.restservices.exception.OrderAlreadyExistException;
import com.gautam.restservices.exception.OrderNotFoundExcpetion;
import com.gautam.restservices.exception.UserNotFoundException;
import com.gautam.restservices.repository.OrderRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderRepository repository;

  @Autowired
  private UserService service;

  public Order createOrder(Long userId, Order order)
      throws UserNotFoundException, OrderAlreadyExistException {
    Optional<User> user = service.getUserById(userId);
    Optional<Order> order1 = repository.findById(order.getOrderId());
    if(order1.isPresent())
      throw new OrderAlreadyExistException("Order Already Exist " + order1);
    order.setUser(user.get());
    return repository.save(order);
  }

  public Order getOrder(Long userId, Long orderId)
      throws UserNotFoundException, OrderNotFoundExcpetion {
    Optional<User> user = service.getUserById(userId);
    List<Order> orderList = repository.findByUser(user.get());
    if(orderList == null)
      throw new OrderNotFoundExcpetion("No such order with user" +
          "id and orderid combination");
    Order order = orderList.stream().filter(x -> x.getOrderId().
        equals(orderId)).collect(Collectors.toList()).get(0);
     return order ;
  }
}
