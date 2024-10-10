package com.korea.product.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.korea.product.Model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
	private int id; 		// 상품아이디
	private String name; 	// 상품이름
	private int stock;		// 상품재고
	private int price;		// 상품가격	
	private LocalDateTime insertDate; 	// 등록날짜
	private LocalDateTime updateDate = LocalDateTime.now(); // 수정날짜
	
	// Entity -> DTO
	public ProductDTO(ProductEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.stock = entity.getStock();
		this.price = entity.getPrice();
		this.insertDate = entity.getInsertDate();
		this.updateDate = entity.getUpdateDate();
	}
	
	// DTO -> Entity
	public static ProductEntity toEntity(ProductDTO dto) {
		return ProductEntity.builder()
							.id(dto.getId())
							.name(dto.getName())
							.stock(dto.getStock())
							.price(dto.getPrice())
							.insertDate(dto.getInsertDate())
							.updateDate(dto.getUpdateDate())
							.build();
		
	}
}














