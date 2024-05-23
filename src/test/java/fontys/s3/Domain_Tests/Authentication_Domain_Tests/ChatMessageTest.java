package fontys.s3.Domain_Tests.Authentication_Domain_Tests;


import fontys.s3.Domain.ChatDomain.ChatMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatMessageTest {

    @Test
    void testChatMessage() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender("user");
        chatMessage.setContent("Hello");
        chatMessage.setTimestamp("2022-01-01T10:00:00");

        assertEquals("user", chatMessage.getSender());
        assertEquals("Hello", chatMessage.getContent());
        assertEquals("2022-01-01T10:00:00", chatMessage.getTimestamp());
    }
}