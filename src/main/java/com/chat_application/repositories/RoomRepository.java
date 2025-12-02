package com.chat_application.repositories;

import com.chat_application.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {

    // get room by room ID (user generated)
    Room findByRoomId(String roomId);
}
