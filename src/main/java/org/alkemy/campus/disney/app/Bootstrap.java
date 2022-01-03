package org.alkemy.campus.disney.app;

import java.time.LocalDate;
import java.util.Random;
import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.model.Appareance.Movie;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.repositories.AppearanceRepository;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.alkemy.campus.disney.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bootstrap {

  private CharacterRepository characterRepository;
  private UserRepository userRepository;
  private AppearanceRepository movieRepository;

  public Bootstrap(CharacterRepository characterRepository, UserRepository userRepository,
      AppearanceRepository movieRepository) {
    this.characterRepository = characterRepository;
    this.userRepository = userRepository;
    this.movieRepository = movieRepository;
  }



  public void demoCharacters() {
    characterRepository.save(new FictionalCharacter("Tony").setAge(40));
    characterRepository.save(new FictionalCharacter("Strange").setAge(40));
    characterRepository.save(new FictionalCharacter("Peter").setAge(20));
    characterRepository.save(new FictionalCharacter("Thor").setAge(700));
  }

  public void demoUsers() {
    DUser user = new DUser();
    user.getRoles().add("ADMIN");
    userRepository.save(
        user.setMail("admin@alkemy.org").setPassword(new BCryptPasswordEncoder().encode("admin")));
  }


  public void demoMovies() {

    characterRepository.findAll().stream().map(Bootstrap::createSoloMovie)
        .forEach(m -> movieRepository.save(m));

  }

  private static Movie createSoloMovie(FictionalCharacter character) {

    Movie movie = new Movie();

    Random rand = new Random();

    Float rating = rand.nextFloat() * 5;

    movie.setTitle(character.getName() + "'s Solo Movie").addCharacter(character).setRating(rating)
        .setReleaseDate(LocalDate.now());

    return movie;
  }

}


