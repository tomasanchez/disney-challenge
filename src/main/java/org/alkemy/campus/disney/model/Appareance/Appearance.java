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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.alkemy.campus.disney.core.db.PersitentEntity;
import org.alkemy.campus.disney.exceptions.MandatoryPropertyException;
import org.alkemy.campus.disney.exceptions.Appareance.InvalidRatingException;
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
    private String title;
    @Column(name = "creationDate", columnDefinition = "DATE")
    private LocalDate creationDate;
    private float rating;
    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Appearance setCreationDate(LocalDate creationDate) {
        this.creationDate = Objects.requireNonNull(creationDate, "Creation date cannot be empty");
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
