/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.controller;

/**
 *
 * @author panha
 */
import com.school.forum.model.ChatMessage;
import com.school.forum.service.MessageService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor(onConstructor = @__(
        @Autowired))
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessageController {

    private final MessageService messageService;

    @CrossOrigin
    @GetMapping("/{room}")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable String forumId) {
        return ResponseEntity.ok(messageService.getMessage(forumId));
    }

}
