package com.korea.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.product.dto.ProductDTO;
import com.korea.product.model.ProductEntity;
import com.korea.product.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
	//조회하기
	public List<ProductDTO> findAll(){
		//DB에 접근해서 데이터를 조회
		//findAll() : select * from product;
		List<ProductEntity> list = productRepository.findAll();
		
		//리스트 안에 들어있는 Entity들을 DTO로 변경
		List<ProductDTO> dtos = list.stream().map(ProductDTO::new).collect(Collectors.toList());
		return dtos;
	}
	
	//추가하기
	public List<ProductDTO> create(ProductDTO dto){
		//dto -> entity
		ProductEntity entity = ProductDTO.toEntity(dto);
		
		//넘어온 데이터를 데이터베이스에 추가
		productRepository.save(entity);
		
		//다시 조회해서 반환
		return findAll();
		
	}
	
	
	
	
	
	
	
	
	
	
	
}




