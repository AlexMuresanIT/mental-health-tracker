package com.health.mental.service;

import com.health.mental.domain.MoodLog;
import com.health.mental.domain.User;
import com.health.mental.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void addUser(final User user) {
    if (isInvalidUserPassword(user.password())) {
      throw new IllegalArgumentException(
          "Password must be at least 7 characters long, contain at least one number and one uppercase letter.");
    }
    if (userRepository.existsByEmail(user.email())) {
      throw new IllegalArgumentException("Email already in use.");
    }
    userRepository.save(user);
  }

  public void changeUserPassword(final String userId, final String newPassword) {
    final var userMaybe = userRepository.findById(userId);
    userMaybe.ifPresent(
        user -> {
          if (isInvalidUserPassword(newPassword) || newPassword.equals(user.password())) {
            throw new IllegalArgumentException(
                "Password must be at least 7 characters long, contain at least one number and one uppercase letter.");
          }
          userRepository.save(User.ofPassword(user, newPassword));
        });
  }

  public Optional<User> findById(final String userId) {
    return userRepository.findById(userId);
  }

  public void saveMoodLogForUser(final User user, final MoodLog moodLog) {
    userRepository.save(User.addMoodLog(user, moodLog));
  }

  private boolean isInvalidUserPassword(final String password) {
    return password == null
        || password.length() < 7
        || !passwordContainsNumber(password)
        || !passwordContainsUppercase(password);
  }

  private static boolean passwordContainsNumber(final String password) {
    return password.chars().anyMatch(Character::isDigit);
  }

  private static boolean passwordContainsUppercase(final String password) {
    return password.chars().anyMatch(Character::isUpperCase);
  }
}
