package com.korea.user.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor

@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//GenerationType.AUTO
	// 데이터베이스에 따라서 자동으로 생성하는 규칙
	// H2와 같은 내장 데이터베이스를 사용하는 경우, 
	// 기본적으로 숫자값이 증가하는 방식으로 id가 실행된다
	
	int id;
	String name;
	String email;
}
