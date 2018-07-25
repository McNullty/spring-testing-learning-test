package com.mladen.cikara.spring.testing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

  private final GreetingService service;

  public GreetingController(final GreetingService service) {
    this.service = service;
  }

  @RequestMapping("/greeting")
  public @ResponseBody
  String greeting() {
    return this.service.greet();
  }
}
