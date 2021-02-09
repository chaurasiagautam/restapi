package com.gautam.restservices.controller;

import com.gautam.restservices.bean.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  @GetMapping("/test")
  public UserDetails getUserDetailsObj(){
    return new UserDetails("Gautam","Chaurasia","Delhi");
  }
}
