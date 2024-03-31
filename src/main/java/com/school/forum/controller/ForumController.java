/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.controller;

import com.school.forum.dto.ForumRequest;
import com.school.forum.model.Forum;
import com.school.forum.service.ForumService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author panha
 */
@RestController
@RequestMapping("/forum")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ForumController {
    
    private final ForumService forumService;
    
    @PostMapping()
    @RolesAllowed("TEACHER")
    public ResponseEntity<Forum> addForum(@RequestBody @Valid ForumRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(forumService.addForum(request));
    }
    
    @GetMapping()
    public List<Forum> getForumsByCourseId(@RequestParam("courseId") String courseId) {
        return forumService.getForumsByCourseId(UUID.fromString(courseId));
    }
}
