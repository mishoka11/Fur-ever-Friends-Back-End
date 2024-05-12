package fontys.s3.Controller;


import fontys.s3.Bussiness.Implementation.User_Impl.CreateUserUseCase;
import fontys.s3.Bussiness.Implementation.User_Impl.GetAllUsersUseCase;
import fontys.s3.Bussiness.Implementation.User_Impl.GetUserUseCase;
import fontys.s3.Bussiness.Implementation.User_Impl.UpdateUserUseCase;
import fontys.s3.Domain.*;
import fontys.s3.Domain.UserDomain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final GetUserUseCase getUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final CreateUserUseCase.DeleteUserUseCase deleteUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        final GetUserResponse userResponse = getUserUseCase.getUser(id);

        if (userResponse != null) {
            return ResponseEntity.ok().body(mapToUser(userResponse));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private User mapToUser(GetUserResponse userResponse) {
        return User.builder()
                .id(userResponse.getUserId())
                .username(userResponse.getUsername())
                .email(userResponse.getEmail())
                .password(userResponse.getPassword())
                .build();
    }

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getUsers() {
        return ResponseEntity.ok(getAllUsersUseCase.getAllUsers(GetAllUsersRequest.builder().build()));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") long userId) {
        deleteUserUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id, @RequestBody @Valid UpdateUserRequest request) {
        request.setUserId(id);
        updateUserUseCase.updateUser(request);
        return ResponseEntity.noContent().build();
    }
}
