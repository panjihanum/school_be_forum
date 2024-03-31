/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.service;

import com.school.forum.dto.ForumRequest;
import com.school.forum.model.Forum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.forum.dao.ForumRepository;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author panha
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ForumService {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private AuthService authService;

    public Forum addForum(ForumRequest forumRequest) {
        Forum forum = new Forum();

        forum.setTeacherId(authService.getUserId());
        forum.setCourseId(forumRequest.getCourseId());
        forum.setTitle(forumRequest.getTitle());
        forum.setDescription(forumRequest.getDescription());
        forum.setEffectiveDate(forumRequest.getEffectiveDate());
        forum.setExpiryDate(forumRequest.getExpiryDate());
        forum.setActive(true);

        return forumRepository.save(forum);
    }

    public List<Forum> getForumsByCourseId(UUID courseId) {
        return forumRepository.findByCourseId(courseId);
    }

    public Forum findById(String forumId) {
        return forumRepository.findById(UUID.fromString(forumId)).get();
    }
}
