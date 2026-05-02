package com.examgrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examgrade.dto.LoginRequest;
import com.examgrade.dto.RegisterRequest;
import com.examgrade.dto.UserDTO;
import com.examgrade.entity.User;
import com.examgrade.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER USER
@PostMapping("/register")
public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {

    User user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setRole(request.getRole());

    User savedUser = userService.registerUser(user);

    return ResponseEntity.ok(mapToDTO(savedUser));
}
@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
    try {
        User user = userService.loginUser(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(mapToDTO(user));

    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
    // GET ALL USERS
   @GetMapping
public List<UserDTO> getAllUsers() {
    return userService.getAllUsers()
            .stream()
            .map(user -> {
               return mapToDTO(user);
            })
            .toList();
}

    // GET USER BY ID
  @GetMapping("/{id}")
public UserDTO getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);

    if (user == null) {
       throw new RuntimeException("User not found with id: " + id);
    }

   return mapToDTO(user);
}
private UserDTO mapToDTO(User user) {
    UserDTO dto = new UserDTO();
    dto.setId(user.getId());
    dto.setName(user.getName());
    dto.setEmail(user.getEmail());
    dto.setRole(user.getRole());
    return dto;
}

}