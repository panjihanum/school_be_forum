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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final ForumService forumService;
    private final MessageService messageService;

    public void sendSocketmessage(SocketIOClient senderClient, ChatMessage message, String room) {
        for (SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
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
                        .forum(message.getForum())
                        .senderId(message.getSenderId())
                        .build()
        );

        sendSocketmessage(senderClient, storedMessage, message.getForum().getId().toString());

    }

    public void saveInfoMessage(SocketIOClient senderClient, String message, String forumId) {
        Forum forum = forumService.findById(forumId);
        ChatMessage storedMessage = messageService.saveMessage(
                ChatMessage.builder()
                        .messageType(MessageType.SERVER)
                        .content(message)
                        .forum(forum)
                        .build()
        );

        sendSocketmessage(senderClient, storedMessage, forum.getId().toString());
    }
}
