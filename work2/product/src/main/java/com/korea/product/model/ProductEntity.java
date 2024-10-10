package com.korea.product.model;

import jakarta.annotation.Generated;
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
@NoArgsConstructor // JPA에서 Entity객체를 데이터베이스에소 조회하려고 할 때 없으면 문제가 발생할 수 있다.
@AllArgsConstructor

@Builder
@Entity
@Table(name="products")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private double price;
}
