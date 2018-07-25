package com.mladen.cikara.spring.testing;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeController.class)
public class WebLayerTest {

  @Autowired
  private MockMvc mockMvc;

  /**
   * In this test we start only web layer with @WebMvcTest annotation. You can further configure
   * which controllers will be started by configuring @WebMvcTest.
   *
   * @throws Exception
   */
  @Test
  void shouldReturnDefaultMessage() throws Exception {
    // @formatter:off
    this.mockMvc
      .perform(get("/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello World")));
    // @formatter:on
  }
}
