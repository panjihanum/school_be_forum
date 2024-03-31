/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ResourceAlreadyExistsException extends RuntimeException {

    private String resourceName;
    private String resourceId;

    public ResourceAlreadyExistsException(String resourceName, Integer resourceId) {
        this(resourceName, resourceId.toString());
    }

    public String getMessage() {
        if (resourceName == null || resourceId == null) {
            return null;
        }
        return String.format("Resource '%s' already registered with id '%s'", resourceName, resourceId);
    }

}
