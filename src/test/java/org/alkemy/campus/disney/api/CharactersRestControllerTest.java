package org.alkemy.campus.disney.api;

import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.model.Character.FictionalCharacterDTO;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
    fcDTO.setName("Edward");
  }

  @Test
  @Transactional
  void whenGet_andNotLoggedIn_thenUnauthorized() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(route))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void wheGet_andLoggedIn_thenOk() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(route))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenPosting_AValidCharacter_andLoggedIn_thenCreated() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(route).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void whenPosting_AValidCharacter_andNotLoggedIn_thenCreated() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(route).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenPosting_AnInValidCharacter_andLoggedIn_thenBadRequest() throws Exception {
    fcDTO.setName(null);
    mockMvc
        .perform(MockMvcRequestBuilders.post(route).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenPatch_AValidCharacter_andLoggedIn_thenOk() throws Exception {
    Mockito.when(characterRepository.findById(0L))
        .thenReturn(Optional.of(new FictionalCharacter()));
    mockMvc
        .perform(MockMvcRequestBuilders.patch(route.concat("/0")).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenPatch_ANotExistingCharacter_andLoggedIn_thenNotFound() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.patch(route.concat("/0")).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }


  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenDelete_AValidCharacter_andLoggedIn_thenOK() throws Exception {
    Mockito.when(characterRepository.findById(0L))
        .thenReturn(Optional.of(new FictionalCharacter()));
    mockMvc
        .perform(MockMvcRequestBuilders.delete(route.concat("/0")).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenDelete_ANotExistingCharacter_andLoggedIn_thenNotFound() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.delete(route.concat("/0")).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  private String getJson() throws Exception {
    return new ObjectMapper().writeValueAsString(fcDTO);
  }
}
