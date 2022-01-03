package org.alkemy.campus.disney.api;

import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.repositories.UserRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

  static String route = "/auth/";

  @MockBean
  private UserRepository userRepository;

  @Autowired
  UserAuthRestController userController;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
    String user = "{\"password\": \"bob\", \"mail\" : \"bob@domain.com\"}";
    mockMvc
        .perform(MockMvcRequestBuilders.post(route.concat("register")).content(user)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void whenPostRequestToUsersAndInValidUser_thenCorrectResponse() throws Exception {
    String user = "{\"password\": \"\", \"mail\" : \"bob@domain.com\"}";

    mockMvc
        .perform(MockMvcRequestBuilders.post(route.concat("register")).content(user)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.password", Is.is("A password must be provided")))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

  }

  @Test
  @Transactional
  public void whenPostRequestAndUserExits_thenCorrectResponse() throws Exception {

    DUser existentUser = new DUser().setMail("existent@mail.com").setPassword("aValidPassword");

    Mockito.when(userRepository.findByMail(existentUser.getMail())).thenReturn(existentUser);

    mockMvc
        .perform(MockMvcRequestBuilders.post(route.concat("register"))
            .content(existentUser.toString()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isConflict())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.mail",
            Is.is("The indicated mail address is already in use")));
  }


}

