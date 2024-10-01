package com.korea.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korea.user.UserDto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.persistence.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repository;
		// 사용자생성
		// 컨트롤러부터 이름과 이메일이 담긴 entity 넘겨받음
		// db에 추가 한 후 추가가 잘 됐는지 조회를 한 데이터를 컨트롤러로 넘겨야한다.
	public List<UserDTO> create(UserEntity entity){
		repository.save(entity); // 데이터베이스에 저장
		
		// List<UserEntity> -> List<UserDTO>로 변환
		return repository.findAll().stream().map(UserDTO :: new).collect(Collectors.toList());
		
	}
	
	//모든유저 조회하기
	public List<UserDTO> getAllUsers(){
		return repository.findAll().stream().map(UserDTO :: new).collect(Collectors.toList());
	}
	
	//email을 통해 한 명의 사용자 검색하기
	public UserDTO getUserByEmail(String email){
		UserEntity entity = repository.findByEmail(email);
		return new UserDTO(entity);
	}
	
	public List<UserDTO> updateUser(UserEntity entity) {
		//Id를 통해서 DB에 저장되어있는 객체를 찾는다.
		Optional<UserEntity> original = repository.findById(entity.getId());
		
		original.ifPresent(userEntitiy -> {
			//이름과 이메일을 객체에 setting을 한다.
			userEntitiy.setName(entity.getName());
			userEntitiy.setEmail(entity.getEmail());
			
		//업데이트된 사용자 정보 저장
		repository.save(userEntitiy);
		});
		return getAllUsers();
		
	}
	
	public boolean deleteUser(int id){
		//id를 통해 유저가 존재하는지 먼저 확인
		Optional<UserEntity> exisit = repository.findById(id);
		
		if(exisit.isPresent()) {
			repository.deleteById(id);
			return true;
		} else {
			return false;
		}
		
	}
	
}





















