package com.health.mental.mapper;

import static com.health.mental.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {UserMapperImpl.class, MoodLogMapperImpl.class})
public class UserMapperTest {

  @Autowired private UserMapper userMapper;

  @Test
  void testUserMappingDoDTO() {
    final var user = getUser(PASSWORD);
    final var mappedUser = userMapper.from(user);

    assertThat(mappedUser).isNotNull();
    assertThat(mappedUser)
        .satisfies(
            dto -> {
              assertThat(dto.name()).isEqualTo(user.name());
              assertThat(dto.email()).isEqualTo(user.email());
              assertThat(dto.gender()).isEqualTo(user.gender());
              assertThat(dto.age()).isEqualTo(user.age());
            });

    final var moodLogDTOs = mappedUser.userMoodLogs();
    assertThat(moodLogDTOs).isNotNull();
    assertThat(moodLogDTOs.size()).isEqualTo(user.userMoodLogs().size());
  }

  @Test
  void testUserMappingToDomain() {
    final var dto = getUserDTO();
    final var domainUser = userMapper.to(dto);

    assertThat(domainUser).isNotNull();
    assertThat(domainUser)
        .satisfies(
            user -> {
              assertThat(user.name()).isEqualTo(dto.name());
              assertThat(user.email()).isEqualTo(dto.email());
              assertThat(user.gender()).isEqualTo(dto.gender());
              assertThat(user.age()).isEqualTo(dto.age());
            });
  }
}
