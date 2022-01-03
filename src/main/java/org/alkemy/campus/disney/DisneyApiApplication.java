package org.alkemy.campus.disney;

import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.alkemy.campus.disney.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DisneyApiApplication {

  private static final Logger log = LoggerFactory.getLogger(DisneyApiApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DisneyApiApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(CharacterRepository fcRepository, UserRepository userRepository) {
    return (args) -> {

      // save a few characters
      if (fcRepository.count() == 0) {
        log.info("Saving Characters...");
        fcRepository.save(new FictionalCharacter("Tony"));
        fcRepository.save(new FictionalCharacter("Strange"));
        fcRepository.save(new FictionalCharacter("Peter"));
        fcRepository.save(new FictionalCharacter("Thor"));
        log.info("Characters saved");
        log.info("");
      }

      // Save an admin user
      if (userRepository.count() == 0) {
        log.info("Creating Admin User...");
        log.info("mail= admin@alkemy.org; password= admin");
        DUser user = new DUser();
        user.getRoles().add("ADMIN");
        userRepository.save(user.setMail("admin@alkemy.org")
            .setPassword(new BCryptPasswordEncoder().encode("admin")));
        log.info("Admin created");
        log.info("");
      }

    };
  }

}
