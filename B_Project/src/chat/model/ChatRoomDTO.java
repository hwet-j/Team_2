package chat.model;

/* 채팅방 정보 */
public class ChatRoomDTO {
    private int room_id;
    private String room_name;

    public ChatRoomDTO(int room_id, String room_name) {
        this.room_id = room_id;
        this.room_name = room_name;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }
}
