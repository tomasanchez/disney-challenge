package org.alkemy.campus.disney.model.Character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FictionalCharacterDTO implements Serializable {

  // --------------------------------------------------------------------------------------------
  // Properties
  // --------------------------------------------------------------------------------------------

  @NotNull
  @NotEmpty
  @NotBlank
  String name;
  String image;
  @Min(value = 0)
  private int age;
  @DecimalMin("0.0")
  private float weight;
  private String story;
  List<Long> appearances = new ArrayList<>();

  // --------------------------------------------------------------------------------------------
  // Getters & Setters
  // --------------------------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public float getWeight() {
    return weight;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public String getStory() {
    return story;
  }

  public void setStory(String story) {
    this.story = story;
  }

  public List<Long> getAppearances() {
    return appearances;
  }

  public void setAppearances(List<Long> appearances) {
    this.appearances = appearances;
  }



}
