/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.constant;

/**
 *
 * @author panha
 */
public enum RoleConstant {
    ADMIN("Administrator"),
    STUDENT("Pelajar"),
    TEACHER("Guru");

    private final String displayName;

    RoleConstant(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
