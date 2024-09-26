package ex02;

//객체 자동 등록하기
//ComponentScanning
//클래스 앞에@Component 어노테이션을붙이면
//패키지에 컴포넌트 어노테이션이 붙어있는 클래스를 찾아서 객체로 만들어 Map에 저장하는 기법

import org.springframe


@Component class Car{};
class SportCar extends Car{};
class Truck extends Car{};
class Engine {};


public class Main {
	public static void main(String[] args) {
		
	}
}
