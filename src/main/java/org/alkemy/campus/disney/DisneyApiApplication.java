package org.alkemy.campus.disney;

import org.alkemy.campus.disney.app.Bootstrap;
import org.alkemy.campus.disney.repositories.AppearanceRepository;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.alkemy.campus.disney.repositories.GenreRepository;
import org.alkemy.campus.disney.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DisneyApiApplication {

  private static final Logger log = LoggerFactory.getLogger(DisneyApiApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DisneyApiApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(CharacterRepository fcRepository, UserRepository userRepository,
      AppearanceRepository movieRepository, GenreRepository genreRepository) {
    return (args) -> {

      Bootstrap bs = new Bootstrap(fcRepository, userRepository, movieRepository, genreRepository);

      // save a few characters
      if (fcRepository.count() == 0) {
        log.info("Saving Characters...");
        bs.demoCharacters();
        log.info("Characters saved");
        log.info("");
      } else {
        log.info("There are already characters - not initializing");
      }

      // Save an admin user
      if (userRepository.count() == 0) {
        log.info("Creating Admin User...");
        log.info("mail= admin@alkemy.org; password= admin");
        bs.demoUsers();
        log.info("Admin created");
        log.info("");
      } else {
        log.info("There are already users - not initializing");
      }

      // Saves a set of Genres
      if (genreRepository.count() == 0) {
        log.info("Saving Genres...");
        bs.demoGenres();
        log.info("Genres created");
        log.info("");
      } else {
        log.info("There are already genres - not initializing");
      }

      // Saves A movi for each character
      if (movieRepository.count() == 0) {
        log.info("Saving Movies...");
        bs.demoMovies();
        log.info("Solo movies created");
        log.info("");
      } else {
        log.info("There already movies - not initializing");
      }



    };
  }

}
