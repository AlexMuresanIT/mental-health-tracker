package com.health.mental.controller;

import com.health.mental.domain.dto.UserDTO;
import com.health.mental.mapper.UserMapper;
import com.health.mental.service.UserService;
import java.util.List;
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
    userService.addUser(userMapper.to(user));
    return ResponseEntity.ok().build();
  }

  @GetMapping("/all")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return ResponseEntity.ok(userMapper.toDTOs(userService.getAllUsers()));
  }
}
