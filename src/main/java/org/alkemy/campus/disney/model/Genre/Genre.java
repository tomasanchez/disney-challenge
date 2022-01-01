package org.alkemy.campus.disney.model.Genre;

import java.util.Objects;
import javax.persistence.Entity;
import org.alkemy.campus.disney.core.db.PersitentEntity;
import org.alkemy.campus.disney.exceptions.MandatoryPropertyException;

/**
 * Represents apparance genres types.
 * 
 * @author Tomás Sánchez
 */
@Entity
public class Genre extends PersitentEntity {

    // --------------------------------------------------------------------------------------------
    // Properties
    // --------------------------------------------------------------------------------------------

    private String name;
    private String image;

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
