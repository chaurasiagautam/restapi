package com.gautam.restservices.exception;

import java.util.Date;

public class CustomExpDetails {

  public Date timeStamp;
  public String message;
  public String expDetails;

  public CustomExpDetails(Date timeStamp, String message, String expDetails) {
    this.timeStamp = timeStamp;
    this.message = message;
    this.expDetails = expDetails;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public String getMessage() {
    return message;
  }

  public String getExpDetails() {
    return expDetails;
  }
}
