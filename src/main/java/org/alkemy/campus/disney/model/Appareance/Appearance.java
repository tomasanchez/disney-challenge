package org.alkemy.campus.disney.model.Appareance;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.alkemy.campus.disney.core.db.PersitentEntity;
import org.alkemy.campus.disney.exceptions.MandatoryPropertyException;
import org.alkemy.campus.disney.exceptions.appearance.InvalidRatingException;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.model.Genre.Genre;


@Entity
@Table(name = "appearances")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Appearance extends PersitentEntity {

  // --------------------------------------------------------------------------------------------
  // Properties
  // --------------------------------------------------------------------------------------------

  private String image;
  @NotNull
  @NotEmpty
  @NotBlank
  private String title;
  @Column(name = "releaseDate", columnDefinition = "DATE")
  private LocalDate releaseDate;
  @DecimalMin("0.0")
  @DecimalMax("5.0")
  private float rating;
  @JsonBackReference
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(name = "characters_appareances", joinColumns = @JoinColumn(name = "appareance"),
      inverseJoinColumns = @JoinColumn(name = "character"))
  Set<FictionalCharacter> characters = new HashSet<>();
  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "genre")
  private Genre genre;

  // --------------------------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------------------------

  public Appearance() {}

  public Appearance(AppearanceDTO dto) {

    setTitle(dto.getTitle());
    setReleaseDate(Objects.requireNonNullElse(dto.getReleaseDate(), LocalDate.now()));
    setRating(Objects.requireNonNullElse(dto.getRating(), 0.0f));

  }

  // --------------------------------------------------------------------------------------------
  // Getters & Setters
  // --------------------------------------------------------------------------------------------

  public String getImage() {
    return image;
  }

  public Appearance setImage(String image) {
    this.image = image;
    return this;
  }

  public String getTitle() {
    return title;
  }

  /**
   * Sets an appareance title.
   * 
   * @param title to be set
   * @return the appareance
   * @throws MandatoryPropertyException
   */
  public Appearance setTitle(String title) {
    this.title = Objects.requireNonNull(title, "Title cannot be null");

    if (this.title.isEmpty()) {
      throw new MandatoryPropertyException("Title cannot be empty");
    }

    return this;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public Appearance setReleaseDate(LocalDate creationDate) {
    this.releaseDate = Objects.requireNonNull(creationDate, "Creation date cannot be empty");
    return this;
  }

  public float getRating() {
    return rating;
  }

  public Genre getGenre() {
    return this.genre;
  }

  public Appearance setGenre(Genre genre) {
    this.genre = Objects.requireNonNull(genre);
    return this;
  }

  /**
   * Sets an appareance rating.
   * 
   * @param rating to be set
   * @return the appareance
   * @throws InvalidRatingException
   */
  public Appearance setRating(float rating) {

    if (rating < 0) {
      throw new InvalidRatingException("The rating cannot be negative.");
    }

    if (rating > 5) {
      throw new InvalidRatingException("The rating cannot be greater than five (5).");
    }

    this.rating = rating;
    return this;
  }

  public Set<FictionalCharacter> getCharacters() {
    return characters;
  }

  // --------------------------------------------------------------------------------------------
  // Methods
  // --------------------------------------------------------------------------------------------

  public Map<String, ?> toShortMap() {
    Map<String, Object> shortMap = new HashMap<>();
    shortMap.put("id", this.getId());
    shortMap.put("image", this.getImage());
    shortMap.put("title", this.getTitle());
    shortMap.put("releaseDate", this.getReleaseDate().toString());
    return shortMap;
  }



  // --------------------------------------------------------------------------------------------
  // Relational Methods
  // --------------------------------------------------------------------------------------------

  public Appearance addCharacter(FictionalCharacter character) {
    getCharacters().add(character);
    character.addAppearance(this);
    return this;
  }

  public Appearance removeCharacter(FictionalCharacter character) {
    getCharacters().remove(character);
    character.removeAppearance(this);
    return this;
  }
}
