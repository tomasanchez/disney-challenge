package org.alkemy.campus.disney.tools.validation.value;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValuesAllowedValidator implements ConstraintValidator<ValuesAllowed, String> {

  private List<String> expectedValues;
  private String returnMessage;

  @Override
  public void initialize(ValuesAllowed requiredIfChecked) {
    expectedValues = Arrays.asList(requiredIfChecked.values()).stream().map(String::toUpperCase)
        .collect(Collectors.toList());
    returnMessage = requiredIfChecked.message().concat(expectedValues.toString());
  }

  @Override
  public boolean isValid(String testValue, ConstraintValidatorContext context) {

    boolean valid = validateValue(testValue);
    if (!valid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(returnMessage).addConstraintViolation();
    }

    return valid;
  }

  private boolean validateValue(String value) {
    try {
      return expectedValues.contains(value.toUpperCase());
    } catch (Exception e) {
      return Objects.isNull(value);
    }
  }
}
