package com.chat_application.controllers;

import com.chat_application.entities.Message;
import com.chat_application.entities.Room;
import com.chat_application.exceptions.RoomNotFoundException;
import com.chat_application.payload.MessageRequest;
import com.chat_application.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@CrossOrigin("http://localhost:5173")
@Controller
public class ChatController {

    @Autowired
    private RoomService roomService;

    public ChatController(RoomService roomService) {
        this.roomService = roomService;
    }

    // for sending and receiving messages

    @MessageMapping("/sendMessage/{roomId}") // and send message to this - /app/sendMessage/roomId;
    @SendTo("/topic/room/{roomId}") // client subscribe to this URL
    public Message sendMessages(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ){

        // check room exist with room ID or not
        Room room = this.roomService.getRoom(roomId);

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        if(room != null){
            this.roomService.saveMessages(room, message);
        }
        else{
            throw new RoomNotFoundException();
        }

        return message;
    }
}
