//package fontys.s3.Configuration_Tests.Websockets;
//
//import fontys.s3.Configuration.WebConfig;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.CorsRegistration;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//class WebConfigTest {
//
//    private WebConfig webConfig;
//    private CorsRegistry corsRegistry;
//
//    @BeforeEach
//    void setUp() {
//        webConfig = new WebConfig();
//        corsRegistry = mock(CorsRegistry.class);
//    }
//
//    @Test
//    void testAddCorsMappings() {
//        CorsRegistration registration = mock(CorsRegistration.class);
//        when(corsRegistry.addMapping("/**")).thenReturn(registration);
//        when(registration.allowedOrigins(anyString())).thenReturn(registration);
//        when(registration.allowedMethods(anyString())).thenReturn(registration);
//        when(registration.allowedHeaders(anyString())).thenReturn(registration);
//        when(registration.allowCredentials(anyBoolean())).thenReturn(registration);
//
//        webConfig.addCorsMappings(corsRegistry);
//
//        verify(corsRegistry).addMapping("/**");
//        verify(registration).allowedOrigins("http://localhost:5174");
//        verify(registration).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//        verify(registration).allowedHeaders("*");
//        verify(registration).allowCredentials(true);
//    }
//}
