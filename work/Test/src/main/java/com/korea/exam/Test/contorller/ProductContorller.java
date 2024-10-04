package com.korea.exam.Test.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.exam.Test.dto.ProductDTO;
import com.korea.exam.Test.service.ProductService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductContorller {
	
	
	private final ProductService service;
	
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO dto) {
		ProductDTO product = service.addProduct(dto);
		return ResponseEntity.ok().body(product);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllProduct(){
		List<ProductDTO> products = service.getAllProduct();
		return ResponseEntity.ok().body(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id){
		List<ProductDTO> products = service.getProductById(id);
		return ResponseEntity.ok().body(products);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDTO dto){
		ProductDTO products = service.updateProduct(id, dto);
		if(products !=null) {
			return ResponseEntity.ok().body(products);
		}
		return ResponseEntity.badRequest().body("수정 오류");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		boolean products = service.deleteProduct(id); 
		if(products) {
			return ResponseEntity.ok("삭제");
		} else {
			return ResponseEntity.badRequest().body("삭제 오류");
		}
	}
}



















