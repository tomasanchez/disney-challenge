package org.alkemy.campus.disney.model.Genre;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.alkemy.campus.disney.core.db.PersitentEntity;
import org.alkemy.campus.disney.exceptions.MandatoryPropertyException;
import org.alkemy.campus.disney.model.Appareance.Appearance;

/**
 * Represents apparance genres types.
 * 
 * @author Tomás Sánchez
 */
@Entity
@Table(name = "genres")
public class Genre extends PersitentEntity {

  // --------------------------------------------------------------------------------------------
  // Properties
  // --------------------------------------------------------------------------------------------

  @NotBlank
  @NotNull
  @NotEmpty
  @Column(unique = true)
  private String name;
  private String image = "";
  @JsonBackReference
  @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
  private Set<Appearance> appearances = new HashSet<>();

  // --------------------------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------------------------

  public Genre() {}

  public Genre(String name) {
    this.name = name;
  }

  // --------------------------------------------------------------------------------------------
  // Constructors
  // --------------------------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public Genre setName(String name) {

    Objects.requireNonNull(name, "Name cannot be null");

    if (name.isEmpty()) {
      throw new MandatoryPropertyException("Name cannot be empty");
    }

    this.name = name;
    return this;
  }

  public String getImage() {
    return image;
  }

  public Genre setImage(String image) {
    this.image = image;
    return this;
  }

  // --------------------------------------------------------------------------------------------
  // Relational Methods
  // --------------------------------------------------------------------------------------------

}
