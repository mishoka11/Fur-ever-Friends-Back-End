package fontys.s3.Bussiness.Login_Implementation_Tests;


import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Bussiness.Implementation.Login_Service.CustomUserDetailsService;
import fontys.s3.Persistence.Entity.UserTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customUserDetailsService = new CustomUserDetailsService(userRepository);
    }

    @Test
    void testLoadUserByUsername_Successful() {
        UserTypeEntity userType = UserTypeEntity.builder().id(1L).typeName("Regular").build();
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("john_doe")
                .password("securepassword")
                .userTypes(Set.of(userType))
                .build();

        when(userRepository.findByUsername("john_doe")).thenReturn(userEntity);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("john_doe");

        assertNotNull(userDetails);
        assertEquals("john_doe", userDetails.getUsername());
        assertEquals("securepassword", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername("unknown_user")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("unknown_user");
        });
    }
}