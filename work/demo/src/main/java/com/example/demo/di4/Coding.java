package com.example.demo.di4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 코딩을 하려면 컴퓨터가 필요하다.
@Component
@RequiredArgsConstructor //생성자 생성
@Getter
public class Coding {
	//	@Autowired
	//	private Computer computer;
//------------------------------------------------------------------------------------//	
	//생성자주입(사용 권장)
	//final이나 @NonNull을 붙히고
	private final Computer computer;
//	public Coding(Computer computer) {
//		this.computer = computer;
//	}
	
	
//------------------------------------------------------------------------------------//	
	//setter 주입
	
//	public void setComputer(Computer computer) {
//		this.computer = computer;
//	}
	
//------------------------------------------------------------------------------------//		
//	public Computer getComputer() {
//		return computer;
//	}
}
