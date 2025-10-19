package com.health.mental.domain;

import java.util.LinkedList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public record User(
    @Id String id,
    String name,
    @Indexed(unique = true) String email,
    Gender gender,
    Integer age,
    String password,
    List<MoodLog> userMoodLogs) {

  public static User ofPassword(final User user, final String newPassword) {
    return new User(
        user.id(),
        user.name(),
        user.email(),
        user.gender(),
        user.age(),
        newPassword,
        user.userMoodLogs());
  }

  public static User ofMoodLogs(final User user, final MoodLog moodLog) {
    final var userCurrentMoodLogs =
        user.userMoodLogs() == null ? new LinkedList<MoodLog>() : user.userMoodLogs();
    userCurrentMoodLogs.add(moodLog);
    return new User(
        user.id(),
        user.name(),
        user.email(),
        user.gender(),
        user.age(),
        user.password(),
        userCurrentMoodLogs);
  }
}
