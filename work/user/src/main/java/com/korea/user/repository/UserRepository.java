package com.korea.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korea.user.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	//id를 기준으로 유저를 검색하는 메서드를 만들기
	//회원가입할 때 중복체크할 때 쓸 기능 또는 로그인시 중복되는 지
	Optional<UserEntity> findByUserId(String id);

	UserEntity findByUserIdAndPwd(String userId, String pwd);
	
}
