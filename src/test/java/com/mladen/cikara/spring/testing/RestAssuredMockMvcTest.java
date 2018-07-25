package com.mladen.cikara.spring.testing;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.containsString;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestAssuredMockMvcTest {

  @Autowired
  private MockMvc mockMvc;

  /**
   * This test starts only spring context and doesn't start Tomcat server. It's using REST assured
   * support for MockMVC.
   *
   * @throws Exception
   */
  @Test
  void greetingShouldReturnDefaultMessage() throws Exception {

    // @formatter:off
    given()
      .log().all()
    .when()
      .get("/greeting")
    .then()
      .log().all()
      .statusCode(HttpStatus.OK.value())
      .contentType(ContentType.TEXT)
      .body(containsString("Hello World"))
      ;

    // @formatter:on

  }

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.mockMvc(this.mockMvc);
  }
}
