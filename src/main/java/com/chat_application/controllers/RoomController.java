package com.chat_application.controllers;

import com.chat_application.entities.Message;
import com.chat_application.entities.Room;
import com.chat_application.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // create room
    @PostMapping("/")
    public ResponseEntity<?> createRoom(@RequestBody String roomId){

        try{
            Room room = this.roomService.createRoom(roomId);

            if(room == null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Room not created");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(room);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room Not created");
        }
    }

    // get Room
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId){

        Room room = this.roomService.getRoom(roomId);

        if(room == null){
            return ResponseEntity.badRequest().body("Room Not found!!");

        }

        return ResponseEntity.ok(room);

    }


    // get room messages
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ){

        Room room = this.roomService.getRoom(roomId);

        if(room == null){
            return ResponseEntity.badRequest().build();
        }

        // get messages
        // pagination
        List<Message> messages = room.getMessages();
        int total = messages.size();

        int start = Math.max(0, total - (page + 1) * size);
        int end   = Math.min(total, start + size);

        List<Message> paginatedMessages = messages.subList(start, end);

        return ResponseEntity.ok(paginatedMessages);
    }
}
