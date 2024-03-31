/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.forum.module;

/**
 *
 * @author panha
 */
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.school.forum.constant.Constants;
import com.school.forum.model.ChatMessage;
import com.school.forum.service.SocketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SocketModule {

    private final SocketIOServer server;

    private final SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
        server.addConnectListener(this.onConnected());
        server.addDisconnectListener(this.onDisconnected());
        server.addEventListener("send_message", ChatMessage.class, this.onChatReceived());
    }

    private DataListener<ChatMessage> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            socketService.saveMessage(senderClient, data);
        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            var params = client.getHandshakeData().getUrlParams();
            String forumId = params.get("forumId").stream().collect(Collectors.joining());
            String username = params.get("username").stream().collect(Collectors.joining());
            client.joinRoom(forumId);
            socketService.saveInfoMessage(client, String.format(Constants.WELCOME_MESSAGE, username), forumId);
            log.info("Socket ID[{}] - forumId[{}] - username [{}]  Connected to chat module through", client.getSessionId().toString(), forumId, username);
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            var params = client.getHandshakeData().getUrlParams();
            String forumId = params.get("forumId").stream().collect(Collectors.joining());
            String username = params.get("username").stream().collect(Collectors.joining());
            socketService.saveInfoMessage(client, String.format(Constants.DISCONNECT_MESSAGE, username), forumId);
            log.info("Socket ID[{}] - forumId[{}] - username [{}]  discnnected to chat module through", client.getSessionId().toString(), forumId, username);
        };
    }

}
