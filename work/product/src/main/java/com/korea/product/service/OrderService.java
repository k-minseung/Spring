package com.korea.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.product.Model.OrderEntity;
import com.korea.product.Model.ProductEntity;
import com.korea.product.dto.OrderDTO;
import com.korea.product.dto.ProductDTO;
import com.korea.product.repository.OrderRepository;
import com.korea.product.repository.ProductRepository;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@Service
public class OrderService {
	
	private final OrderRepository o_repository;
	
	private final ProductRepository p_repository;
	
	//전체주문내역 조회하기
	//한건의 데이터 -> 하나의 엔티티 객체
	//2개 이상의 데이터가 조회될 수 있음
	//하나의 메서드는 하나의 값만 반환할 수가 있다.
	public List<OrderDTO> getAllOrderTotalPrices(){
		List<Object[]> list = o_repository.findAllOrderTotalPrices();
		return OrderDTO.toListOrderDTO(list);
	}
	
	
	//주문하기 기능
	public List<ProductDTO> save(OrderDTO dto){
		
		//상품이 존재하는 지 확인
		Optional<ProductEntity> option = p_repository.findById(dto.getProductId());
		ProductEntity productEntity;
		if(option.isPresent()) {
			productEntity = option.get();
		}else {
			// IllegalAccessException : 잘못된 또는 부적절한 메서드에 전달됐을 때
			throw new IllegalArgumentException("상품을 찾을 수 없습니다.");
		}
		
		// 재고 확인
        if (productEntity.getStock() < dto.getProductCount()) {
            throw new RuntimeException("재고가 부족합니다. 현재 재고: " + productEntity.getStock());
        }
		
		//주문하기
		OrderEntity order = OrderEntity.builder()
                .product(productEntity)
                .productCount(dto.getProductCount())
                .build();
		o_repository.save(order); // DB에 주문내역 저장
		
		// 재고 감소
		productEntity.setStock(productEntity.getStock() - dto.getProductCount());
        p_repository.save(productEntity); //DB에 수정된 재고로 업데이트
		
        //전체 목록 반환하기
        List<ProductDTO> dtos = p_repository.findAll()
        								.stream().map(ProductDTO::new).collect(Collectors.toList());
		
		return dtos;
	}
	
}















