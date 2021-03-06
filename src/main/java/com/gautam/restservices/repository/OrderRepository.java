package com.gautam.restservices.repository;

import com.gautam.restservices.entities.Order;
import com.gautam.restservices.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findByUser(User user);
}
