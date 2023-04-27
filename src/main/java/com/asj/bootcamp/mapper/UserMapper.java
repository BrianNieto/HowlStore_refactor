package com.asj.bootcamp.mapper;

import com.asj.bootcamp.dto.UserCompletoDTO;
import com.asj.bootcamp.dto.UserLoginDTO;
import com.asj.bootcamp.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserCompletoDTO userEntityToUserCompletoDTO(User user);

    User userCompletoDTOToUserEntity(UserCompletoDTO userDTO);

    User userLoginDTOToUserEntity(UserLoginDTO userLoginDTO);

}