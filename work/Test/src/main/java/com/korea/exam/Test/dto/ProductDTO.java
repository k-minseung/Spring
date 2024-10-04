package com.korea.exam.Test.dto;

import com.korea.exam.Test.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
	
	private long id;
	private String name;
	private Double price;
	private Integer stock;
	
	public ProductDTO(ProductEntity entity) {
		this.id =entity.getId();
		this.name = entity.getName();
		this.price = entity.getPrice();
		this.stock = entity.getStock();
	}
	
	public static ProductEntity entity (ProductDTO dto) {
		return ProductEntity.builder()
					.id(dto.getId())
					.name(dto.getName())
					.price(dto.getPrice())
					.stock(dto.getStock())
					.build();
	}
}
