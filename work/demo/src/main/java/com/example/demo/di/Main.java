//package com.example.demo.di;
//
//import java.util.Map;
//import java.util.Set;
//import java.util.HashMap;
//
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import com.google.common.reflect.ClassPath;
//
//
//// 객체 자동 등록하기
//// 스프링부트에서 어떻게 객체를 자동으로 만들어 놓고 시작할 수 있는가?
//// Component Scaning
//// 클래스 앞에 @Component 어노테이션을 붙이고 패키지에 컴포넌트 어노테이션이 붙어있는 클래스를 찾아서 객체로 만들어서 맵으로 저장하는 기법
//
//
//@Component class Car{};
//@Component class SportCar extends Car{};
//@Component class Truck extends Car{};
//@Component class Engine{};
//
//// 객체 컨테이너역할의 클래스
//class AppContext{
//	Map map; // 객체 저장소 (객체 컨테이너)
//	
//	public AppContext() {
//		map = new HashMap(); // 객체를 저장하기 위한 map을 준비
//		doComponentScan(); // 컴포넌트 스캐닝을 해서 map에 저장해주는 메서드
//	}
//	
//	private void doComponentScan () {
//		try {
//			// 1. 패키지내의 클래스 목록을 가져온다
//			// 2. 반복문으로 클래스를 하나씩 읽어서 @Component 가 붙어있는지 확인
//			// 3. @Component가 붙어있으면 객체를 생성해서 Map에 저장
//			
//			// ClassLoader는 JVM에서 클래스를 동적으로 로드하고, 애플리케이션이 사용할 수 있도록
//			// 메모리에 적재하는 역할을 한다.
//			ClassLoader classLoader = AppContext.class.getClassLoader();
//			
//			// ClassPath는 구아바에서 제공하는 기능으로, 클래스 경로상의 모든 클래스를 탐색하고
//			// 사용할 수 있게해준다.
//			ClassPath classPath = ClassPath.from(classLoader);
//			
//			// 지정한 패키지내의 최상위 클래스들을 가져온다.
//			// 이 메서드는 지정된 패키지에서 상위레벨 클래스를 탐색하고, 그 결과로 ClassPath.ClassInfo 객체들의 집합(Set)을 반환
//			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.example.demo.di");
//			
//			// 위에서 얻은 클래스 정보를 반복으로 처리한다.
//			// 각 ClassPath.ClassInfo 객체는 특정 클래스에 대한 정보를 나타낸다.
//			for(ClassPath.ClassInfo classInfo : set) {
//				//현재의 ClassInfo객체를 실제로 로드된 Class로 변환한다.
//				//이 메서드는 해당 클래스의 정보를 기반으로 JVM에서 해당 클래스를 로드하여 Class객체를 반환한다.
//				Class clazz= classInfo.load();
//				
//				// 해당 클래스에 @Component 어노테이션이 붙어있는지 확인
//				Component component = (Component)clazz.getAnnotation(Component.class);
//				
//				if(component != null) {
//					//클래스 이름의 첫 글자를 소문자로 변환하여 id로 사용한다.
//					//클래스 이름의 첫 글자를 소문자로 변환하는 이유 :
//					//스프링에서 빈을 생성할 때, 기본적으로 클래스 이름의 첫 글자를 소문자로 사용하기 때문.
//					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
//					
//					map.put(id, clazz.newInstance());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//	
//	//객체 찾기
//	//by Name
//	Object getBean(String Key) { // 만들어진 객체를 사용하기 위해서 getBean메서드 생성
//		return map.get(Key);
//	}
//	
//	//by type
//	Object getBean(Class clazz) {
//		for(Object obj : map.values()) {
//			if(clazz.isInstance(obj)) {
//				return obj;
//			}
//		}
//		return null;
//	}
//}
//
//
//public class Main {
//	public static void main(String[] args) {
//		AppContext ac = new AppContext();
//		
//		Car car = (Car)ac.getBean("car");
//		System.out.println("car= " + car);
//		
//		Engine engine = (Engine)ac.getBean("engine"); //byName으로 객체를 검색
//		System.out.println("engine= " + engine);
//		
//		Car car2 = (Car)ac.getBean(Car.class); //byType으로 객체를 검색
//		System.out.println("car2= " + car2);
//	}
//}
