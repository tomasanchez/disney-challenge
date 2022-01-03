package org.alkemy.campus.disney.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alkemy.campus.disney.model.Character.FictionalCharacterDTO;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class CharactersRestControllerTest {

  private static final String route = "/characters";

  @MockBean
  private CharacterRepository characterRepository;

  @Autowired
  CharactersRestController charactersController;

  @Autowired
  private MockMvc mockMvc;

  private FictionalCharacterDTO fcDTO;


  @BeforeEach
  void setUp() {
    fcDTO = new FictionalCharacterDTO();
  }

  @Test
  @Transactional
  void whenRequestGetWithOutLogIn_thenUnauthorized() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(route))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenRequestGetHavingLogIn_thenOk() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(route))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenPostingAValidCharacter_andLoggedIn_thenCreated() throws Exception {
    fcDTO.setName("Edward");
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(route).content(new ObjectMapper().writeValueAsString(fcDTO))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void whenPostingAValidCharacter_andNotLoggedIn_thenCreated() throws Exception {
    fcDTO.setName("Edward");
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(route).content(new ObjectMapper().writeValueAsString(fcDTO))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

}
