package org.alkemy.campus.disney.model.Appareance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AppearanceDTO {

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
  private Float rating;
  List<Long> characters = new ArrayList<>();
  @Min(value = 0)
  private Long genre;
  private Integer type;

  // --------------------------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------------------------

  public AppearanceDTO() {}

  // --------------------------------------------------------------------------------------------
  // Getters & Setters
  // --------------------------------------------------------------------------------------------

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public List<Long> getCharacters() {
    return characters;
  }

  public void setCharacters(List<Long> characters) {
    this.characters = characters;
  }

  public Long getGenre() {
    return genre;
  }

  public void setGenre(long genre) {
    this.genre = genre;
  }

  public Integer getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }


}
