package com.il.sod.services;

import org.springframework.stereotype.Service;

@Service
public class MyService {

  public String getMyValue(String val) {
    return "Service===> " + val;
  }
}
