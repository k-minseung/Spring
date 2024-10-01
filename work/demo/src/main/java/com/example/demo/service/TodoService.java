package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.presistence.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Component 어노테이션이 붙은 클래스는 Bean으로 만들어준다.
//@Service 컴포넌트는 @Component의 자식 컴포넌트
//스프링이 @Service도 찾아서 Bean으로 만들어준다.

//@Component의 자식 컴포넌트 종류
//@Service
//@Repository
//@Controller
//@RestController -> Controller + ResponseBody = 직렬화 해서 반환
//컴포넌트를 구분함으로 써 클래스의 용도를 좀 더 구체적으로 구분할 수 있다.
@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
	
	//TodoRepository 생성자 주입하기
	private final TodoRepository repository;

	//메서드형태로 비즈니스로직을 구현
	public String testService() {
		//TodoEntity생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		//TodoEntity 저장
		//save() : insert
		repository.save(entity);
		//TodoEntity 검색
		//findById(entity.getId()) 기본키를 이용해 db에 저장된 TodoEntity찾기
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
	
	// controller 에서 넘어온 데이터를 검증하고 Todo 테이블에 저장
	public List<TodoEntity> create(TodoEntity entity){
		// 매개변수 넘어온 Entity가 유효한지 검사
		validate(entity);
		// 넘어온 Entity에 문제가 없을 때 db에 추가
		repository.save(entity);
		
		// {}는 slf4j에서 제공하는 플레이스홀더로 두 번째 매개변수로 전달된 
		// entity.getId()값이 대입되어 출력된다.
		log.info("Entity Id : {} is saved", entity.getId());
		
		// 넘어온 entity로 부터 userId를 가지고 db에서 조회된 내용을 List에 묶어서 반환
		// SELECT * FROM TodoEntity wher userid = 'temporary-user';
		// 조회 된 결과를 List에 묶어서 반환하겠다.
		return repository.findByUserId(entity.getUserId());
	
	} // create end
	

	
	
	private void validate(TodoEntity entity) {
		
		if(entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
		}
		
		// userId가 안 넘어왔을 때
		if(entity.getUserId()==null) {
			log.warn("Unknown user");
			throw new RuntimeException("Unknown user");
		}
	}// validate end
	
	//userId 를 넘겨받아 해당유저가 추가한 Todo를 모두 조회하는 메서드
	public List<TodoEntity> retrieve(String userId){
		return repository.findByUserId(userId);
	}
	
	
	
	@GetMapping
	public ResponseEntity<?> retrieveTodoList(){
		String temporaryUserId = "temporary-user";
		// 서비스 레이어의 retrieve 메서드를 이용해 TodoEntity가 담겨있는 리스트를 반환받아 entities에 저장한다.
		List<TodoEntity> entities = retrieve(temporaryUserId);
		// 자바 스트림을 이용해 반환된 리스트를 TodoDTO객체로 변환하고 리스트로 변환하여 dtos에 저장한다
		// map(TodoDTO :: new) -> .map(entity -> new TodoDTO(entity))
		List<TodoDTO> dtos = entities.stream().map(TodoDTO :: new).collect(Collectors.toList());
		// 변환된 dtos리스트를 이용해 ResponseDTO에 담고 ResponseEntity를 이용해 응답을 반환한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
		
	}
	
	// Entity를 수정하고, 수정한 entity를 확인 할 수 있는 update메서드
	// entity 매개변수는 수정된 값이 들어있다
	public List<TodoEntity> update(TodoEntity entity){
		//1. 수정 할 entity가 유효한지 확인
		validate(entity);
		
		//존재하지않는 entity는 업데이트 할 수 없음
		// 기존에 저장되어있는 데이터를 조회
		Optional<TodoEntity> original = repository.findById(entity.getId());
		
//		original.ifPresent(todo -> {
//			// 반환된 TodoEntity가 존재하면 값을 새 Entity로 덮어쓴다.
//			todo.setTitle(entity.getTitle());
//			todo.setDone(entity.isDone());
//			
//			// 데이터베이스에 새 값을 저장한다.
//			repository.save(todo);
//		});
		
		if(original.isPresent()) {
			TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			repository.save(todo);
		}
		// 수정이 잘 됐는지 확인하기 위해 retrieve 메서드를 실행한 결과를 반환
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(TodoEntity entity){
		// 1. 엔티티가 유효한지 확인
		validate(entity);
//		Optional<TodoEntity> original = repository.findById(entity.getId());
		try {
			// 엔티티를 삭제
			repository.delete(entity);
				 
			
		}catch (Exception e) {
			// 예외 발생시 id와 exception을 로그로 찍는다.
			log.error("error deleting entity " + entity.getId());
			// 컨트롤러로 exception 을 날린다
			// 데이터베이스 내부 로직을 캡슐화 하기 위해 e를 반환하지 않고 새 exception 객체를 반환한다.
			throw new RuntimeException("error deleting entity " + entity.getId());
		}
		// 새 todo 리스트를 가져와 반환한다.
		return retrieve(entity.getUserId());
	}
}










