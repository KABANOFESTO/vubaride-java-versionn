package rw.transport.vubaride.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import rw.transport.vubaride.model.User;
import rw.transport.vubaride.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    // Endpoint for user registration
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)                    
                    .body("Error during user registration: " + e.getMessage());
        }
                    
    }
                    
                    
    // Endpoint for user login
                    
    @PostMapping("/login")
                    
    public ResponseEntity<String> login(@RequestBody User user) {
                    
        try {
                    
            boolean isAuthenticated = userService.authenticateUser(user.getEmail(), user.getPassword());
            if (isAuthenticated) {
                    
                return ResponseEntity.ok("Login successful");
                    
            } else {
                    
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body
                    ("Invalid credentials");
            }
                    
        } catch (Exception e) {
                    
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during login: " + e.getMessage());
        }
    }

                    
    // Endpoint to fetch all users
    @GetMapping("/all")
                    
    public ResponseEntity<List<User>> getUsers() {
        try {
                    
            List<User> users = userService.getUsers();
            return ResponseEntity.ok(users);
                    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
                    

    // Endpoint to fetch user by email
                    
    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String 
                    email) {
        try {
            User theUser = userService.getUser(email);
                    
            return ResponseEntity.ok(theUser);
        } catch (UsernameNotFoundException e) {
                    
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        } catch (Exception e) {
                    
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user");
        }
                    
    }

                    
    // Endpoint to delete a user by email
    @DeleteMapping("/delete/{email}")
                    
    public ResponseEntity<String> deleteUser(@PathVariable("email") String email) {
        try {
                    
            userService.deleteUser(email);
            return ResponseEntity.ok("User deleted successfully");
                    
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting user: " + e.getMessage());
        }
    }
}
