package com.korea.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	//id중복첸크 메서드 만들기
	//id를 db로 전달해서 조회되면 false 안되면 true를 반환
	//true 면 아이디생성가능 , false 면 아이디 생성 불가능
	public boolean isIdDuplicate(String id) {
		Optional<UserEntity> user = userRepository.findByUserId(id);
		return !user.isPresent();//!(not)을 붙이지 않으면조회가 되면 true 안되면 false라는 뜻
	}
	
	
	//회원추가  
	public List<UserDTO> insert(UserDTO dto){
		
		//dto -> entity
		UserEntity entity = UserDTO.toEntity(dto);
		
		//entity를 db에 저장
		userRepository.save(entity);
		
		//List<UserEntity> -> List<UserDTO>
		return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
		
	}
	
	//유저 전체 조회
	public List<UserDTO> allUser() {	
		List<UserDTO> list = userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
		return list;
	}
	
	//로그인
	//아이디랑 비밀번호를 받는다.
	public UserEntity getByCredential(String userId, String pwd) {
		return userRepository.findByUserIdAndPwd(userId,pwd);
	}
	
	
	
	
	
}
