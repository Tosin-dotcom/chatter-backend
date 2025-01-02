package com.tosin.chatter.room;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/meeting")
public class RoomController {

    @PostMapping("start")
    public ResponseEntity<String> startMeeting(@RequestParam String name) {

        String meetingRoomId = UUID.randomUUID().toString().substring(0, 7);

        return new ResponseEntity<>(meetingRoomId, HttpStatus.CREATED);
    }

}
