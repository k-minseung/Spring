package com.korea.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product.dto.ProductDTO;
import com.korea.product.model.ProductEntity;
import com.korea.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor

public class ProductController {
	
	private final ProductService service;
	
	//상품추가 -제품 10가지 넣는 코드
	@PostMapping
	public ResponseEntity<?> addProduct(){
	   return ResponseEntity.ok().body(service.addProduct());
	}

//	//상품추가
//	@PostMapping
//	public ResponseEntity<?> addPorduct(@RequestBody ProductDTO dto){
//		ProductDTO createProduct = service.addProduct(dto);
//		return ResponseEntity.ok().body(createProduct);
//	}
	
	//상품조회 // 사용법 -> ?name= &pric=
	@GetMapping
	public ResponseEntity<?> getFilteredProducts(
	     @RequestParam(value = "minPrice", required = false) Double minPrice,
	     @RequestParam(value = "name", required = false) String name) {
	     List<ProductDTO> products = service.getFilteredProducts(minPrice, name);
	     return ResponseEntity.ok().body(products);
	}
	     
	//상품 수정
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDTO dto){
		ProductDTO products = service.updateProduct(id, dto);
		if(products !=null) {
			return ResponseEntity.ok().body(products);
		}
		return ResponseEntity.badRequest().body("업데이트가 안됐습니다");
	}
	
	//상품삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		boolean isDeleted = service.deleteProduct(id);
		if(isDeleted) {
			return ResponseEntity.ok("isDeleted");
		} else {
			return ResponseEntity.badRequest().body("Not Deleting");
		}
		
	}
	
	
}
























