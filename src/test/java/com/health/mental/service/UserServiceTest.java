package com.health.mental.service;

import static com.health.mental.TestUtil.PASSWORD;
import static com.health.mental.TestUtil.getUser;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.health.mental.domain.User;
import com.health.mental.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock private UserRepository userRepository;

  @InjectMocks private UserService userService;

  private final ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

  @Test
  void shouldAddUserSuccessfully() {
    final var user = getUser("Password1");

    userService.addUser(user);

    verify(userRepository).save(captor.capture());
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenAddingUserWithWeakPassword() {
    final var user = getUser(PASSWORD);

    assertThrows((IllegalArgumentException.class), () -> userService.addUser(user));
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenAddingUserWithExistingEmail() {
    final var user = getUser("Password1");

    when(userRepository.existsByEmail(any())).thenReturn(true);

    assertThrows((IllegalArgumentException.class), () -> userService.addUser(user));
  }

  @Test
  void shouldChangeUserPasswordSuccessfully() {
    final var user = getUser("Password1");
    when(userRepository.findById(user.id())).thenReturn(Optional.of(user));

    userService.changeUserPassword(user.id(), "Newpass1");

    verify(userRepository).save(captor.capture());
  }
}
