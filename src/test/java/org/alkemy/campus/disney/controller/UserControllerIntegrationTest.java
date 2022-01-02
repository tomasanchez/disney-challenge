package org.alkemy.campus.disney.controller;

import org.alkemy.campus.disney.repositories.UserRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

  static String route = "/auth/";

  @MockBean
  private UserRepository userRepository;

  @Autowired
  UserController userController;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
    String user = "{\"password\": \"bob\", \"mail\" : \"bob@domain.com\"}";
    mockMvc
        .perform(MockMvcRequestBuilders.post(route.concat("register")).content(user)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void whenPostRequestToUsersAndInValidUser_thenCorrectResponse() throws Exception {
    String user = "{\"password\": null, \"mail\" : \"bob@domain.com\"}";

    mockMvc
        .perform(MockMvcRequestBuilders.post(route.concat("register")).content(user)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.password", Is.is("A password must be provided")))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

  }
}

