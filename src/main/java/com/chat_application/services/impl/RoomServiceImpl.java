package com.chat_application.services.impl;

import com.chat_application.entities.Message;
import com.chat_application.entities.Room;
import com.chat_application.exceptions.RoomNotFoundException;
import com.chat_application.repositories.RoomRepository;
import com.chat_application.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room createRoom(String roomId) throws Exception {
        // check before creating
        Room room = getRoom(roomId);

        if(room != null){
            // Room ID already occupied
            throw new Exception("Room already exist with same ID " + roomId);
        }

        //  CREATE NEW ROOM
        room = new Room();
        room.setRoomId(roomId);
        System.out.println(room.getRoomId());

        room = this.roomRepository.save(room);

        return room;

    }

    @Override
    public Room getRoom(String roomId) {

        return this.roomRepository.findByRoomId(roomId);
    }

    @Override
    public Room saveMessages(Room room, Message message) {

        room.getMessages().add(message);
        return this.roomRepository.save(room);
    }

}
