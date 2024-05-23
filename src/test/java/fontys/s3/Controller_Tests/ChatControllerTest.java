package fontys.s3.Controller_Tests;


import fontys.s3.Controller.ChatController;
import fontys.s3.Domain.ChatDomain.ChatMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ChatControllerTest {

    @InjectMocks
    private ChatController chatController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void send_ShouldReturnTheSameMessage() {
        // Arrange
        ChatMessage message = new ChatMessage();
        message.setContent("Hello, world!");

        // Act
        ChatMessage result = chatController.send(message);

        // Assert
        assertEquals(message, result);
    }
}