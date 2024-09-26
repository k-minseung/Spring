package com.example.demo.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("laptop")
public class Laptop implements Computer {
	
	@Override
	public int getScreenWidth() {
		// TODO Auto-generated method stub
		return 1600;
	}
	
}
