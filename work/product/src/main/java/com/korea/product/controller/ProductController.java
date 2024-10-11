package com.korea.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product.Model.ProductEntity;
import com.korea.product.dto.ProductDTO;
import com.korea.product.dto.ResponseDTO;
import com.korea.product.service.ProductService;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {
	
	private final ProductService service;
	
	//전체 조회하기
	@GetMapping
	public ResponseEntity<?> productList(){
	   List<ProductDTO> dtos = service.findAll();
	   ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
	                                 .data(dtos).build();
	   return ResponseEntity.ok().body(response);
	}

	
	//추가하기
	   @PostMapping
	   public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto){
	      List<ProductDTO> dtos = service.create(dto);
	      ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
	                                       .data(dtos)
	                                       .build();
	      return ResponseEntity.ok().body(response);
	   }

}






























