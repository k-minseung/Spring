package com.korea.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.user.dto.ResponseDTO;
import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.security.TokenProvider;
import com.korea.user.service.UserService;

import ch.qos.logback.core.subst.Token;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	private final TokenProvider tokenProvider;
	
	@GetMapping("/allUsers")
	public ResponseEntity<?> allUsers(){
		List<UserDTO> dto = userService.allUser();
		ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().data(dto).build();
		return ResponseEntity.ok().body(response);
	}
	
	
	@GetMapping("idCheck")
	public ResponseEntity<?> isIdDuplicate(@RequestBody UserDTO dto){
		boolean check = userService.isIdDuplicate(dto.getUserId());
		ResponseDTO<Boolean> response = ResponseDTO.<Boolean>builder().value(check).build();
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/signup")
	//DTO로 넘겨야할 것들
	//id 
	//pwd
	//name
	//email
	public ResponseEntity<?> signup(@RequestBody UserDTO dto){
		List<UserDTO> dtos = userService.insert(dto);
		ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO dto){
		UserEntity user = userService.getByCredential(dto.getUserId(),dto.getPwd());
		
		//조회가 된다면
		if(user != null) {
			//토큰 발급해주기
			final String token = tokenProvider.create(user);
			
			final UserDTO responseUserDTO = UserDTO.builder()
													.userId(user.getUserId())
													.idx(user.getIdx())
													.name(user.getName())
													.email(user.getEmail())
													.token(token)
													.build();
			return ResponseEntity.ok().body(responseUserDTO);
		} else {
			ResponseDTO responseDTO = ResponseDTO.builder().error("Login faild").build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
		
	}
	
	
}








































