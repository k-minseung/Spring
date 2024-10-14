package com.korea.user.dto;

import com.korea.user.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	//Entity -> DTO
	public UserDTO(UserEntity entity) {
		this.idx = entity.getIdx();
		this.userId = entity.getUserId();
		this.pwd = entity.getPwd();
		this.name = entity.getName();
		this.email = entity.getEmail();
	}
	
	//DTO -> Entity
	public static UserEntity toEntity (UserDTO dto) {
		return UserEntity.builder()
					.idx(dto.getIdx())
					.userId(dto.getUserId())
					.pwd(dto.getPwd())
					.name(dto.getName())
					.email(dto.getEmail())
					.build();
	}
}
