package chat.model;

/* 채팅방 참여자 */
public class ChatInviteDTO {
	private int invite_id;
    private int room_id;
    private String invite_user;

    public ChatInviteDTO(int invite_id, int room_id, String invite_user) {
    	this.invite_id = invite_id;
        this.room_id = room_id;
        this.invite_user = invite_user;
    }
    
    public int getInvite_id() {
    	return invite_id;
    }
    
    public void setInvite_id(int invite_id) {
    	this.invite_id = invite_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getInvite_user() {
        return invite_user;
    }

    public void setInvite_user(String invite_user) {
        this.invite_user = invite_user;
    }
}
