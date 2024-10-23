package com.example.product.kim.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.product.kim.dto.ProductDTO;
import com.example.product.kim.model.ProductEntity;
import com.example.product.kim.persistence.ProductRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository repository;
	
	public List<ProductDTO> findAll(){
		List<ProductEntity> list = repository.findAll();
		List<ProductDTO> dtos = list.stream().map(ProductDTO::new).collect(Collectors.toList());
		return dtos;
		
	}
	
	public List<ProductDTO> create(ProductDTO dto){
		ProductEntity entity = ProductDTO.toEntity(dto);
		ProductEntity dtos = repository.save(entity);
		return findAll();
	}
	
	
	
	
}
