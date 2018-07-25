package com.mladen.cikara.spring.testing;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@WebMvcTest(controllers = { GreetingController.class })
public class WebMockTest {

  private MockMvc mockMvc;

  @MockBean
  private GreetingService service;

  /**
   * Tests only web layer. Server is not started and only GreetingController is put in context. We
   * must mock service or context starting will fail. </br>
   * </br>
   * This test is also configured to create documentation for GreetingController
   *
   * @throws Exception
   */
  @Test
  void greetingShouldReturnMessageFromService() throws Exception {
    when(this.service.greet()).thenReturn("Hello Mock");

    // @formatter:off

    this.mockMvc
      .perform(get("/greeting"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello Mock")))
        .andDo(document("greeting"));

    // @formatter:on

  }

  @BeforeEach
  public void setUp(final WebApplicationContext webApplicationContext,
      final RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(restDocumentation))
        .build();
  }
}
