package com.korea.exam.Test.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.exam.Test.dto.ProductDTO;
import com.korea.exam.Test.model.ProductEntity;
import com.korea.exam.Test.presistence.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



@Service
@AllArgsConstructor
@Builder
@Data
public class ProductService {
	
	private final ProductRepository repository;
	
	//추가
	public ProductDTO addProduct(ProductDTO dto) {
		ProductEntity entity = ProductDTO.entity(dto);
		ProductEntity savedEntity = repository.save(entity);
		return new ProductDTO(savedEntity);
	}
	
	//조회
	public List<ProductDTO> getAllProduct(){
		return repository.findAll().stream().map(ProductDTO :: new).collect(Collectors.toList());
	}
	
	public  List<ProductDTO> getProductById(int id){
		Optional<ProductEntity> entity = repository.findById(id);
		return entity.stream().map(ProductDTO :: new).collect(Collectors.toList());
	}
	
	//수정
	public ProductDTO updateProduct(int id, ProductDTO dto){
		Optional<ProductEntity> original = repository.findById(id);
		
		if(original.isPresent()) {
			ProductEntity entity = original.get();
			entity.setName(dto.getName());
			entity.setPrice(dto.getPrice());
			entity.setStock(dto.getStock());
			repository.save(entity);
			return new ProductDTO(entity);
		}
		return null;
	}
	
	//삭제
	public boolean deleteProduct(int id) {
		Optional<ProductEntity> original = repository.findById(id);
		
		if(original.isPresent()) {
			repository.deleteById(id);
            return true;
		}
		return false;
	
	}
}

































