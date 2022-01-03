package org.alkemy.campus.disney.model.Character;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.alkemy.campus.disney.core.db.PersitentEntity;
import org.alkemy.campus.disney.exceptions.character.InvalidAgeException;
import org.alkemy.campus.disney.exceptions.character.InvalidWeigthException;
import org.alkemy.campus.disney.model.Appareance.Appearance;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "characters")
public class FictionalCharacter extends PersitentEntity {

  // --------------------------------------------------------------------------------------------
  // Properties
  // --------------------------------------------------------------------------------------------

  private String image = "";
  @NotNull
  @NotEmpty
  @NotBlank
  private String name;
  @Min(value = 0)
  private int age;
  @DecimalMin("0.0")
  private float weight;
  private String story;
  @JsonManagedReference
  @ManyToMany(mappedBy = "characters",
      cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @Fetch(FetchMode.JOIN)
  private Set<Appearance> appearances = new HashSet<>();

  // --------------------------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------------------------


  public FictionalCharacter() {}

  public FictionalCharacter(String name) {
    setName(name);
  }

  public FictionalCharacter(FictionalCharacterDTO dto) {
    setImage(dto.getImage());
    setAge((int) Objects.requireNonNullElse(dto.getAge(), 0));
    setName(dto.getName());
    setWeight((float) Objects.requireNonNullElse(dto.getWeight(), 0.0f));
    setStory(dto.getStory());
  }

  // --------------------------------------------------------------------------------------------
  // Getters & Setters
  // --------------------------------------------------------------------------------------------

  public String getImage() {
    return image;
  }

  public FictionalCharacter setImage(String image) {
    this.image = image;
    return this;
  }

  public String getName() {
    return name;
  }

  public FictionalCharacter setName(String name) {
    this.name = name;
    return this;
  }

  public int getAge() {
    return age;
  }

  /**
   * Sets a character age.
   * 
   * @param age to be set
   * @return the character.
   * @throws InvalidAgeException
   */
  public FictionalCharacter setAge(int age) {

    if (age < 0) {
      throw new InvalidAgeException();
    }

    this.age = age;
    return this;
  }

  public float getWeight() {
    return weight;
  }

  /**
   * Sets the character's weight.
   * 
   * @param weight to be set
   * @return the character
   * @throws InvalidWeigthException
   */
  public FictionalCharacter setWeight(float weight) {

    if (weight < 0.0) {
      throw new InvalidWeigthException();
    }

    this.weight = weight;
    return this;
  }

  public String getStory() {
    return story;
  }

  public FictionalCharacter setStory(String story) {
    this.story = story;
    return this;
  }

  public Set<Appearance> getAppearances() {
    return this.appearances;
  }

  // --------------------------------------------------------------------------------------------
  // Methods
  // --------------------------------------------------------------------------------------------

  public Map<String, String> toShortMap() {
    Map<String, String> shortMap = new HashMap<>();
    shortMap.put("image", this.getImage());
    shortMap.put("name", this.getName());
    return shortMap;
  }

  public FictionalCharacter update(FictionalCharacterDTO dto) {

    this.name = Objects.requireNonNullElse(dto.getName(), name);
    this.image = Objects.requireNonNullElse(dto.getImage(), Objects.isNull(image) ? "" : image);
    this.age = Objects.requireNonNullElse(dto.getAge(), age);
    this.weight = Objects.requireNonNullElse(dto.getWeight(), weight);

    return this;
  }
  // --------------------------------------------------------------------------------------------
  // Relational Methods
  // --------------------------------------------------------------------------------------------

  public FictionalCharacter addAppearance(Appearance appareance) {
    getAppearances().add(appareance);
    return this;
  }

  public FictionalCharacter removeAppearance(Appearance appareance) {
    getAppearances().remove(appareance);
    return this;
  }

}
