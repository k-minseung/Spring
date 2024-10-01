package com.korea.user.UserDto;

import com.korea.user.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserDTO {

	private int id;
	private String name;
	private String email;

//	public UserDTO ToDto(UserEntity entity) {
//		return UserDTO.builder().id(entity.getId()).name(entity.getName()).email(entity.getEmail()).build();
//	}
	
public UserDTO(final UserEntity entity) {
	this.id = entity.getId();
	this.name = entity.getName();
	this.email = entity.getEmail();
}

public static UserEntity toEntity(UserDTO dto) {
	return UserEntity.builder().id(dto.getId()).name(dto.getName()).email(dto.getEmail()).build();
}
}