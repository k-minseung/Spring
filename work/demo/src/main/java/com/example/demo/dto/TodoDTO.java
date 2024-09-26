package com.example.demo.dto;

import com.example.demo.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
	//DTO에는 userId가 없다
	//userId는 애플린케이션과 DB에서 사용자를 구별하기 위한
	//고유 식별자로 사용하기 떄문에 숨길수 있으면 숨기는것이 좋다.
	private String id;
	private String title;
	private boolean done;
	
	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.id = entity.getTitle();
		this.done = entity.isDone();
	}
}
