# 💬 ChatApp-backend

ChatApp Backend is a **real-time, room-based chat server** built using **Spring Boot**.  
It enables users to **create chat rooms, join rooms using a shared code, and exchange messages in real time** using **WebSocket and STOMP**.

---

## 🚀 Features

- 💬 Real-time messaging using **WebSocket & STOMP**
- 🏠 Room-based chat architecture
- ➕ Create chat rooms dynamically
- 🔑 Join rooms using a unique room code
- 🔄 Message broadcasting to all users in a room
- 🧠 Lightweight in-memory room handling (no login required)

---

## 🛠️ Tech Stack

- **Java**
- **Spring Boot**
- **Spring WebSocket**
- **STOMP Protocol**
- **Maven**

---

## 🧩 How It Works

1. Client connects to backend via WebSocket
2. User creates or joins a room using a room code
3. Client subscribes to a room-specific topic
4. Messages are sent to the backend using STOMP
5. Backend broadcasts messages to all subscribers in that room

---

## 🔗 Frontend Code

This backend is consumed by the ChatApp frontend application.

➡️ **Frontend Repository:**  
https://github.com/lalit2506verma/chatapp-frontend

---

## 📂 Project Structure

```text
src/main/java
 ├── config        # WebSocket & STOMP configuration
 ├── controller    # Message controllers
 ├── model         # Message & room models
 ├── service       # Room management logic
 └── ChatAppApplication.java
