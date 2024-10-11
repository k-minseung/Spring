package com.korea.product.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name ="Orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int orderId; 		//주문번호
	
	@ManyToOne // ProductEntity와 다대일 관계를 설정
	@JoinColumn(name="id",nullable = false)
	private ProductEntity product; // 상품번호
	
	private int productCount;
	
	@CreationTimestamp
	private String orderDate;
}




