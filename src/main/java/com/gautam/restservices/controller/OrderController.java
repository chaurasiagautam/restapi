package com.gautam.restservices.controller;

import com.gautam.restservices.entities.Order;
import com.gautam.restservices.entities.User;
import com.gautam.restservices.exception.OrderAlreadyExistException;
import com.gautam.restservices.exception.OrderNotFoundExcpetion;
import com.gautam.restservices.exception.UserNotFoundException;
import com.gautam.restservices.services.OrderService;
import com.gautam.restservices.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class OrderController {

  @Autowired
  private UserService userService;

  @Autowired
  private OrderService orderService;

  @GetMapping(value = "/{userId}/order")
  public List<Order> getAllOrderByUser(@PathVariable Long userId) throws UserNotFoundException {
      Optional<User> user = userService.getUserById(userId);
    System.out.println(user.get());
      return user.get().getOrders();
  }

  @PostMapping(value = "{userId}/order")
  public Order createOrder(@PathVariable Long userId, @RequestBody Order order)
      throws UserNotFoundException, OrderAlreadyExistException {
    return orderService.createOrder(userId,order);
  }

  @GetMapping(value = "/{userId}/{orderId}")
  public Order getOrder(@PathVariable Long userId, @PathVariable Long orderId)
      throws OrderNotFoundExcpetion, UserNotFoundException {
    return orderService.getOrder(userId,orderId);
  }
}
