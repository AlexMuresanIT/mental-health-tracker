package com.health.mental.controller;

import com.health.mental.domain.dto.UserDTO;
import com.health.mental.mapper.UserMapper;
import com.health.mental.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(final UserService userService, final UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @PostMapping("/add")
  public ResponseEntity<Void> addUser(@RequestBody final UserDTO user) {
    try {
      userService.addUser(userMapper.to(user));
      return ResponseEntity.ok().build();
    } catch (final IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{userId}/change-password")
  public ResponseEntity<Void> changeUserPassword(
      @PathVariable final String userId, @RequestBody final String newPassword) {
    try {
      userService.changeUserPassword(userId, newPassword);
      return ResponseEntity.ok().build();
    } catch (final IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable final String userId) {
    return userService
        .findById(userId)
        .map(user -> ResponseEntity.ok(userMapper.from(user)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
