package com.health.mental.mapper;

import com.health.mental.domain.User;
import com.health.mental.domain.dto.UserDTO;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.WARN,
    builder = @Builder(disableBuilder = true),
    uses = {MoodLogMapper.class})
public interface UserMapper {

  List<UserDTO> toDTOs(List<User> users);

  UserDTO from(User user);

  User to(UserDTO userDTO);
}
