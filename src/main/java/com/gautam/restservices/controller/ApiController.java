package com.gautam.restservices.controller;

import com.gautam.restservices.bean.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  @GetMapping("/userdetails")
  public UserDetails getUserDetailsObj(){
    return new UserDetails("Gautam","Kashyap","Delhi");
  }
}
