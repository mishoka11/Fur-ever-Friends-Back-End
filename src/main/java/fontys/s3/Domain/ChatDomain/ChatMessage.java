package fontys.s3.Domain.ChatDomain;

import lombok.Data;

@Data
public class ChatMessage {
    private String sender;
    private String content;
    private String timestamp;


}