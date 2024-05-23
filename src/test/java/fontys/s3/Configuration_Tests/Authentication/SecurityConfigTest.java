//package fontys.s3.Configuration_Tests.Authentication;
//
//import fontys.s3.Configuration.security.auth.AuthenticationRequestFilter;
//import fontys.s3.Configuration.security.auth.SecurityConfig;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@Import(SecurityConfig.class)
//class SecurityConfigTest {
//
//    @Autowired
//    private SecurityConfig securityConfig;
//
//    @Autowired
//    private AuthenticationConfiguration authenticationConfiguration;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    void securityFilterChain_shouldConfigureHttpSecurity() throws Exception {
//        mockMvc.perform(get("/some-secure-url"))
//                .andExpect(status().isUnauthorized()); // Assuming /some-secure-url is secured and requires authentication
//    }
//
//    @Test
//    void authenticationManager_shouldReturnNonNull() throws Exception {
//        assertNotNull(securityConfig.authenticationManager(authenticationConfiguration));
//    }
//}
