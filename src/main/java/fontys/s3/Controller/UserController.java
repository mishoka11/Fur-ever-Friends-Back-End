package fontys.s3.Controller;

import fontys.s3.Domain.UserDomain.CreateUserRequest;
import fontys.s3.Domain.UserDomain.CreateUserResponse;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") long userId) {
        userService.deleteUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("id") long id, @RequestBody CreateUserRequest request) {
        request.setUserId(id);
        userService.updateUser(request);
    }
}
