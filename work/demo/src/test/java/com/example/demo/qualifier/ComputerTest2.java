package com.example.demo.qualifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//Test에 필요한 의존성을 대신 제공
public class ComputerTest2 {
	
	@Autowired
	Computer computer; // 주입을 해주려고 할 때 이 자리에 Desktop과 Laptop 모두 들어올 수 있다.
	
	@Test
	public void widthTest() {
		System.out.println(computer.getScreenWidth());
	}
}
