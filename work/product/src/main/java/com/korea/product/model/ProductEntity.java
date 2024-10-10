package com.korea.product.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 		// 상품아이디
	
	private String name; 	// 상품이름
	private int stock;		// 상품재고
	private int price;		// 상품가격	
	
	@CreationTimestamp //insert 쿼리가 발생할 때 시간 값을 적용시켜주는 어노테이션
	private LocalDateTime insertDate; 	// 등록날짜
	
	@UpdateTimestamp   //update 쿼리가 발생할 때 시간 값을 적용시켜주는 어노테이션
	//LocalDateTime.now() : 현재 시간을 저장
	private LocalDateTime updateDate = LocalDateTime.now(); // 수정날짜
	
	
}
