package chat.model;

import java.util.Date;

/* 채팅방 대화기록 */
public class ChatMessageDTO {
	private int message_id;
    private int room_id;
    private String sender_id;
    private String content;
    private Date created_at;
    
	public ChatMessageDTO(int message_id, int room_id, String sender_id, String content, Date created_at) {
		super();
		this.message_id = message_id;
		this.room_id = room_id;
		this.sender_id = sender_id;
		this.content = content;
		this.created_at = created_at;
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
    
    
    
    
    
}
