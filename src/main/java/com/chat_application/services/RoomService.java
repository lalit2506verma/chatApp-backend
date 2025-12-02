package com.chat_application.services;

import com.chat_application.entities.Message;
import com.chat_application.entities.Room;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {

    // create Room
    Room createRoom(String roomId) throws Exception;

    // Get Room
    Room getRoom(String roomId);

    // save messages
    Room saveMessages(Room room, Message message);
}
