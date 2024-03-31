/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.exception;

/**
 *
 * @author panha
 */
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String resourceId;

    public ResourceNotFoundException(String resourceName, UUID resourceId) {
        this(resourceName, resourceId.toString());
    }

    public String getMessage() {
        if (resourceName == null || resourceId == null) {
            return null;
        }
        return String.format("Resource '%s' not found with id '%s'", resourceName, resourceId);
    }

}
