package com.gautam.restservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table (name = "orders")
public class Order {

  @Id
  @GeneratedValue
  private Long orderId;

  @NotEmpty(message = "order description is mandatory !!, Please provide.")
  private String orderDescription;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private User user;

  public Order() {
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getOrderDescription() {
    return orderDescription;
  }

  public void setOrderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Order{" +
        "orderId=" + orderId +
        ", orderDescription='" + orderDescription + '\'' +
        ", user=" + user +
        '}';
  }
}
