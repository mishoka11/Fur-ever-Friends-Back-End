package fontys.s3.Bussiness.Implementation.Login_Service;

import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import fontys.s3.Persistence.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(user.getUsername(), user.getPassword(),
                user.getUserTypes().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getTypeName()))
                        .collect(Collectors.toList()));
    }
}
