/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.service;

/**
 *
 * @author panha
 */
import com.school.forum.dao.ChatMessageRepository;
import com.school.forum.model.ChatMessage;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(
        @Autowired))
public class MessageService {

    private final ChatMessageRepository messageRepository;

    public List<ChatMessage> getMessage(String forumId) {
        return messageRepository.findAllByForumId(UUID.fromString(forumId));
    }

    public ChatMessage saveMessage(ChatMessage message) {
        return messageRepository.save(message);
    }
}
