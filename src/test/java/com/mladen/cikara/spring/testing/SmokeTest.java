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
   * This is sanity check that will fail when application context can't start. It also checks that
   * controller is loaded in context and that is autowired in test. Server is not started with this
   * test. </br>
   * </br>
   * NOTE: with this simple project with only one controller there is only about 1 second difference
   * between starting server or not. Server will be started if annotation @SpringBootTest is
   * configured with webEnvironment = WebEnvironment.RANDOM_PORT.
   *
   * @throws Exception
   */
  @Test
  void contextLoads() throws Exception {
    assertThat(this.homeController).isNotNull();
  }

}
