package com.tosin.chatter.handler;

import org.springframework.lang.NonNull;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SignalingHandler extends BinaryWebSocketHandler {


    private final ConcurrentHashMap<String, ConcurrentHashMap<String, WebSocketSession>> roomSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        String roomId = getRoomIdFromQuery(session);
        String participantId = session.getId();

        roomSessions.computeIfAbsent(roomId, k -> new ConcurrentHashMap<>()).put(participantId, session);
        System.out.println("Participant joined room: " + roomId);
    }

    @Override
    protected void handleBinaryMessage(@NonNull WebSocketSession session, @NonNull BinaryMessage message) throws Exception {

        String roomId = getRoomIdFromSession(session);

        if (roomId == null) {
            session.sendMessage(new TextMessage("Error: Room ID not found for session."));
            return;
        }

        ConcurrentHashMap<String, WebSocketSession> room = roomSessions.get(roomId);

        if (room == null) {
            session.sendMessage(new TextMessage("Error: Room does not exist."));
            return;
        }

        ByteBuffer payload = message.getPayload();

        for (WebSocketSession participantSession : room.values()) {
            if (participantSession.isOpen() && !participantSession.equals(session)) {
                participantSession.sendMessage(new BinaryMessage(payload));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, @NonNull CloseStatus status) {

        roomSessions.forEach((roomId, participants) -> participants.remove(session.getId()));
        System.out.println("Session disconnected: " + session.getId());
    }


    private String getRoomIdFromQuery(WebSocketSession session) {

        String query = session.getUri().getQuery();
        if (query != null && query.contains("roomId=")) {
            return query.split("roomId=")[1];
        }
        return null;
    }

    private String getRoomIdFromSession(WebSocketSession session) {

        return roomSessions.entrySet().stream()
                .filter(entry -> entry.getValue().containsValue(session))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

}
