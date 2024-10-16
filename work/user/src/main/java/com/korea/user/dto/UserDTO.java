package com.korea.user.dto;

import com.korea.user.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//속성
//token
//idx
//id
//pwd
//name
//email

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String token;
	private int idx;
	private String userId;
	private String pwd;
	private String name;
	private String email;
	
	public UserDTO(UserEntity entity) {
		this.idx = entity.getIdx();
		this.userId = entity.getUserId();
		this.pwd = entity.getPwd();
		this.name = entity.getName();
		this.email = entity.getEmail();
	}
	
	//dto -> entity
	public static UserEntity toEntity(UserDTO dto) {
		return UserEntity.builder()
				.userId(dto.getUserId())
				.pwd(dto.getPwd())
				.name(dto.getName())
				.email(dto.getEmail())
				.build();
	}
	
	
	
	
	
	
}
