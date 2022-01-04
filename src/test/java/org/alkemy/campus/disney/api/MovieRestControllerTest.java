package org.alkemy.campus.disney.api;

import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.alkemy.campus.disney.model.Appareance.AppearanceDTO;
import org.alkemy.campus.disney.model.Appareance.Movie;
import org.alkemy.campus.disney.repositories.AppearanceRepository;
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
public class MovieRestControllerTest {

  private static final String route = "/movies";

  @MockBean
  private AppearanceRepository movieRepository;

  @Autowired
  MovieRestController movieController;

  @Autowired
  private MockMvc mockMvc;

  private AppearanceDTO dto;


  @BeforeEach
  void setUp() {
    dto = new AppearanceDTO();
    dto.setTitle("Avengers");
    dto.setGenre(null);
    dto.setCharacters(null);
    dto.setRating(null);
  }

  @Test
  @Transactional
  void whenGetMovies_andNotLoggedIn_thenUnauthorized() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(route))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void wheGetMovies_andLoggedIn_thenOk() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(route))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenPosting_AValidAppearance_andLoggedIn_thenCreated() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(route).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void whenPosting_AValidAppearance_andNotLoggedIn_thenCreated() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(route).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenPosting_AnInValidAppearance_andLoggedIn_thenBadRequest() throws Exception {
    dto.setTitle(null);
    mockMvc
        .perform(MockMvcRequestBuilders.post(route).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenPut_AValidAppearance_andLoggedIn_thenOk() throws Exception {
    Mockito.when(movieRepository.findById(0L)).thenReturn(Optional.of(new Movie()));
    mockMvc
        .perform(MockMvcRequestBuilders.put(route.concat("/0")).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenPut_ANotExistingAppearance_andLoggedIn_thenIsCreatedOk() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.put(route.concat("/0")).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenDelete_AValidAppearance_andLoggedIn_thenOK() throws Exception {
    Mockito.when(movieRepository.findById(0L)).thenReturn(Optional.of(new Movie()));
    mockMvc
        .perform(MockMvcRequestBuilders.delete(route.concat("/0")).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @WithUserDetails("admin@alkemy.org")
  void whenDelete_ANotExistingAppearance_andLoggedIn_thenNotFound() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.delete(route.concat("/0")).content(getJson())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  private String getJson() throws Exception {
    return new ObjectMapper().writeValueAsString(dto);
  }
}
