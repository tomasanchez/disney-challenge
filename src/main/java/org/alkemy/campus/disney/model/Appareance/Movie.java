package org.alkemy.campus.disney.model.Appareance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Movie extends Appareance {
}
