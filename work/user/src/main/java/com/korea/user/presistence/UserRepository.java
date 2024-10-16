package com.korea.user.presistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

	//아이디를 기준으로 유저를 검색하는 메서드
	//회원가입할 때 중복체크할 때 쓸거임
	Optional<UserEntity> findByUserId(String id);

	UserEntity findByuserIdAndPwd(String userId, String pwd);

	

	
}








