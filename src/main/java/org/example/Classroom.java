package org.example;

public class Classroom {
    private int roomId;
    private int capacity;

    public Classroom(int roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
