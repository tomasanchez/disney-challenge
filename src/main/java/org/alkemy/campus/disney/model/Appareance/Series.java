package org.alkemy.campus.disney.model.Appareance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Series extends Appearance {

  public Series() {}

  public Series(AppearanceDTO dto) {
    super(dto);
  }

}
