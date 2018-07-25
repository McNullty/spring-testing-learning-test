package com.mladen.cikara.spring.testing;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

  public String greet() {
    return "Hello World";
  }
}
