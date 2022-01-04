package org.alkemy.campus.disney.app;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.alkemy.campus.disney.auth.DUser;
import org.alkemy.campus.disney.model.Appareance.Movie;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.model.Genre.Genre;
import org.alkemy.campus.disney.repositories.AppearanceRepository;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.alkemy.campus.disney.repositories.GenreRepository;
import org.alkemy.campus.disney.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bootstrap {

  private CharacterRepository characterRepository;
  private UserRepository userRepository;
  private AppearanceRepository movieRepository;
  private GenreRepository genreRepository;

  public Bootstrap(CharacterRepository characterRepository, UserRepository userRepository,
      AppearanceRepository movieRepository, GenreRepository genreRepository) {
    this.characterRepository = characterRepository;
    this.userRepository = userRepository;
    this.movieRepository = movieRepository;
    this.genreRepository = genreRepository;
  }

  /**
   * Initializes a serues of different characters with names and age. Should be called before movies
   * are created.
   */
  public void demoCharacters() {
    characterRepository.save(new FictionalCharacter("Tony").setAge(40));
    characterRepository.save(new FictionalCharacter("Strange").setAge(40));
    characterRepository.save(new FictionalCharacter("Peter").setAge(20));
    characterRepository.save(new FictionalCharacter("Thor").setAge(1500));
    characterRepository.save(new FictionalCharacter("Vision"));
    characterRepository.save(new FictionalCharacter("Ultron"));
  }

  /**
   * Initializes an admin user.
   */
  public void demoUsers() {
    DUser user = new DUser();
    user.getRoles().add("ADMIN");
    userRepository.save(
        user.setMail("admin@alkemy.org").setPassword(new BCryptPasswordEncoder().encode("admin")));
  }

  /**
   * Initializes a series of different genres. Should be called before movies are created.
   */
  public void demoGenres() {
    genreRepository.save(new Genre("Action"));
    genreRepository.save(new Genre("Horror"));
    genreRepository.save(new Genre("Thriler"));
    genreRepository.save(new Genre("Comedy"));
    genreRepository.save(new Genre("Drama"));
  }

  /**
   * Initializes a movie for each character.
   */
  public void demoMovies() {
    characterRepository.findAll().stream().map(Bootstrap::createSoloMovie).forEach(m -> {
      m.setGenre(genreRepository.findAll().stream().findAny().orElse(null));
      movieRepository.save(m);
    });
  }

  private static Movie createSoloMovie(FictionalCharacter character) {

    Movie movie = new Movie();

    Random rand = new Random();

    Float rating = rand.nextFloat() * 5;

    movie.setTitle(character.getName() + "'s Solo Movie").addCharacter(character).setRating(rating)
        .setReleaseDate(randomDate());

    return movie;
  }


  private static LocalDate randomDate() {
    long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
    long maxDay = LocalDate.now().toEpochDay();
    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
    return LocalDate.ofEpochDay(randomDay);
  }

}


