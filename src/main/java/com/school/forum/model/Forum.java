/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "forums")
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "teacher_id", columnDefinition = "uuid", nullable = false)
    private UUID teacherId;

    @Column(name = "course_id", columnDefinition = "uuid", nullable = false)
    private UUID courseId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "effective_date", nullable = false)
    private Date effectiveDate;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive;
}
