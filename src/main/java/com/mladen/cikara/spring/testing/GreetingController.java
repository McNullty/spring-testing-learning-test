package com.mladen.cikara.spring.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

  private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

  private final GreetingService service;

  public GreetingController(final GreetingService service) {
    this.service = service;
  }

  @RequestMapping("/greeting")
  public @ResponseBody
  String greeting() {
    LOGGER.info("Greeting endpoint");

    try {
      Thread.sleep(2000);
    } catch (final InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return this.service.greet();
  }
}
