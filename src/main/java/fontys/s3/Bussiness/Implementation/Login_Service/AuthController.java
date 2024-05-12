package fontys.s3.Bussiness.Implementation.Login_Service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    //@Autowired
    //private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//            );
//
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String token = jwtTokenUtil.generateToken(userDetails);
//
//            return ResponseEntity.ok(new AuthResponse(token));
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(401).body("Invalid username or password");
//        }
//    }

    // Additional method to create user if needed
    // (this is  for demo purpose)
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        // Create user logic here (e.g., save user to database)
        // Use Spring Security's UserDetailsService to load user by username
        return ResponseEntity.ok("User created successfully");
    }
}
