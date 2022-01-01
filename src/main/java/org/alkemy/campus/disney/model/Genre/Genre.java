package org.alkemy.campus.disney.model.Genre;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.alkemy.campus.disney.core.db.PersitentEntity;
import org.alkemy.campus.disney.exceptions.MandatoryPropertyException;
import org.alkemy.campus.disney.model.Appareance.Appareance;

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

    private String name;
    private String image;

    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private Set<Appareance> apparances = new HashSet<>();

    // --------------------------------------------------------------------------------------------
    // Constructors
    // --------------------------------------------------------------------------------------------

    public Genre() {}

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
