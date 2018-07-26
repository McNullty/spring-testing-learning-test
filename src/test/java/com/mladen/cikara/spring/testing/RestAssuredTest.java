package com.mladen.cikara.spring.testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestAssuredTest {

  @LocalServerPort
  private int port;

  private RequestSpecification spec;

  /**
   * This test starts Tomcat server ant then makes HTTP request using REST Assured framework.
   *
   * @throws Exception
   */
  @Test
  void greetingShouldReturnDefaultMessage() throws Exception {

    // @formatter:off
    given(this.spec)
      .accept(ContentType.TEXT)
      .filter(document("restassured-greeting"))
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
  void setup(final RestDocumentationContextProvider restDocumentation) {
    RestAssured.port = this.port;

    this.spec = new RequestSpecBuilder()
        .addFilter(documentationConfiguration(restDocumentation))
        .build();
  }
}
