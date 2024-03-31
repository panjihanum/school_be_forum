/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.service;

/**
 *
 * @author panha
 */
import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;
import com.school.forum.constant.MessageType;
import com.school.forum.model.ChatMessage;
import com.school.forum.model.Forum;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final ForumService forumService;
    private final MessageService messageService;

    public void sendSocketmessage(SocketIOClient senderClient, ChatMessage message, String forumId) {
        for (SocketIOClient client : senderClient.getNamespace().getRoomOperations(forumId).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent("read_message", message);
            }
        }
    }

    public void saveMessage(SocketIOClient senderClient, ChatMessage message) {

        ChatMessage storedMessage = messageService.saveMessage(
                ChatMessage.builder()
                        .messageType(MessageType.CLIENT)
                        .content(message.getContent())
                        .forumId(message.getForumId())
                        .senderId(message.getSenderId())
                        .build()
        );

        sendSocketmessage(senderClient, storedMessage, message.getForumId().toString());

    }

    public void saveInfoMessage(SocketIOClient senderClient, String message, String forumId) {
        ChatMessage storedMessage = messageService.saveMessage(
                ChatMessage.builder()
                        .messageType(MessageType.SERVER)
                        .content(message)
                        .forumId(UUID.fromString(forumId))
                        .build()
        );

        sendSocketmessage(senderClient, storedMessage, forumId);
    }
}
