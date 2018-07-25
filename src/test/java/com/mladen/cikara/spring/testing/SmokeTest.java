package com.mladen.cikara.spring.testing;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SmokeTest {

  @Autowired
  private HomeController homeController;

  /**
   * This is sanity check that will fail when application context can't start. It
   * also checks that controller is loaded in context and that is aotowired in
   * test.
   *
   * @throws Exception
   */
  @Test
  void contextLoads() throws Exception {
    assertThat(homeController).isNotNull();
  }

}
