package org.alkemy.campus.disney.model.Appareance;

import java.time.LocalDate;
import java.util.HashSet;
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
import javax.persistence.Table;
import org.alkemy.campus.disney.core.db.PersitentEntity;
import org.alkemy.campus.disney.exceptions.MandatoryPropertyException;
import org.alkemy.campus.disney.exceptions.Appareance.InvalidRatingException;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;

@Entity
@Table(name = "appareances")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Appareance extends PersitentEntity {

    private String image;
    private String title;

    @Column(name = "creationDate", columnDefinition = "DATE")
    private LocalDate creationDate;

    private float rating;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "characters_appareances", joinColumns = @JoinColumn(name = "appareance"),
            inverseJoinColumns = @JoinColumn(name = "character"))
    Set<FictionalCharacter> characters = new HashSet<>();

    public Appareance() {}

    public String getImage() {
        return image;
    }

    public Appareance setImage(String image) {
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
    public Appareance setTitle(String title) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");

        if (this.title.isEmpty()) {
            throw new MandatoryPropertyException("Title cannot be empty");
        }

        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Appareance setCreationDate(LocalDate creationDate) {
        this.creationDate = Objects.requireNonNull(creationDate, "Creation date cannot be empty");
        return this;
    }

    public float getRating() {
        return rating;
    }

    /**
     * Sets an appareance rating.
     * 
     * @param rating to be set
     * @return the appareance
     * @throws InvalidRatingException
     */
    public Appareance setRating(float rating) {

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

    public Appareance addCharacter(FictionalCharacter character) {
        getCharacters().add(character);
        character.addAppareance(this);
        return this;
    }

    public Appareance removeCharacter(FictionalCharacter character) {
        getCharacters().remove(character);
        character.removeAppareance(this);
        return this;
    }
}
