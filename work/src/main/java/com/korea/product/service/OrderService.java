package com.korea.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.product.dto.OrderDTO;
import com.korea.product.dto.ProductDTO;
import com.korea.product.model.OrderEntity;
import com.korea.product.model.ProductEntity;
import com.korea.product.persistence.OrderRepository;
import com.korea.product.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	
	private final ProductRepository productRepository;
	
	//전체조회하기
	//한건의 데이터 -> 하나의 엔티티 객체
	//2개이상의 데이터가 조회될 수도 있음
	//하나의 메서드는 하나의 값만 반환할 수가 있다.
	public List<OrderDTO> getAllOrderTotalPrices(){
		//DB에 접근해서 데이터를 조회
		List<Object[]> list = orderRepository.findAllOrderTotalPrices();
		
		return OrderDTO.toListOrderDTO(list);
	}
	
	//주문하기 기능
	public List<ProductDTO> save(OrderDTO dto){
		//productId와 productCount
		
		//상품 존재여부를 확인
		//SELECT * FROM PRODUCT;
		Optional<ProductEntity> option = productRepository.findById(dto.getProductId());
		ProductEntity productEntity;
		
		//상품이 조회가 되면
		if(option.isPresent()) {
			//엔티티를 저장
			productEntity = option.get();
		} else {
			//IllegalArgumentException : 잘못된 또는 부적절한 인수가 메서드에 전달됐을 때
			//발생하는 예외
			throw new IllegalArgumentException("상품을 찾을 수 없다");
		}
		
		//재고확인
		if(productEntity.getProductStock() < dto.getProductCount()) {
			throw new RuntimeException("재고가 부족합니다. 현재 재고 : " + productEntity.getProductStock());
		}
		
		//주문하기
		OrderEntity order = OrderEntity.builder()
								.product(productEntity)
								.productCount(dto.getProductCount())
								.build();
		
		//DB에 주문내역 저장하기
		orderRepository.save(order);
		
		//재고 감소
		productEntity.setProductStock(productEntity.getProductStock() - dto.getProductCount());
		
		//db에 수정된 재고로 업데이트
		productRepository.save(productEntity);
		
		List<ProductDTO> dtos = productRepository.findAll().stream()
									.map(entity ->new ProductDTO(entity))
									.collect(Collectors.toList());
		
		return dtos;
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
}
