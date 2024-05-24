package fontys.s3.Configuration_Tests.Websockets;

import fontys.s3.Configuration.WebSocketConfig;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

import static org.mockito.Mockito.*;

class WebSocketConfigTest {

    @Test
    void testRegisterStompEndpoints() {
        WebSocketConfig config = new WebSocketConfig();
        StompEndpointRegistry registry = mock(StompEndpointRegistry.class);
        StompWebSocketEndpointRegistration registration = mock(StompWebSocketEndpointRegistration.class);

        when(registry.addEndpoint("/chat")).thenReturn(registration);
        when(registration.setAllowedOrigins(anyString())).thenReturn(registration);

        config.registerStompEndpoints(registry);

        verify(registry).addEndpoint("/chat");
        verify(registration).setAllowedOrigins("http://localhost:5173");
        verify(registration).withSockJS();
    }
}
