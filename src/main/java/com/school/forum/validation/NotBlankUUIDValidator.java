/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.validation;

/**
 *
 * @author panha
 */
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.UUID;

public class NotBlankUUIDValidator implements ConstraintValidator<NotBlankUUID, UUID> {

    @Override
    public void initialize(NotBlankUUID constraintAnnotation) {
    }

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context) {
        return value != null && !value.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"));
    }
}
