package com.korea.product.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.korea.product.Model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
	
	private int orderId;         	 // 주문 ID
    private int productId;			 // 상품 Id
    private String productName; 	 // 상품 이름
    private int productCount;    	 // 주문 개수
    private int productPrice;   	 // 상품 가격
    private int totalPrice;     	 // 총 가격 (주문 개수 * 상품 가격)
    private String orderDate; 		 // 주문날짜 
    
    
    public static List<OrderDTO> toListOrderDTO(List<Object[]> list) {
    	
    	//Object[] 데이터를 OrderDTO로 변환
    	return list.stream().map(result -> OrderDTO.builder()
                        .orderId(((int) result[0])) 		
                        .productName((String) result[1]) 	
                        .productCount(((int) result[2]))	
                        .productPrice(((int) result[3]))	
                        .totalPrice(((int) result[4])) 		
                        .orderDate(((String) result[5]))	
                        .build())
                .collect(Collectors.toList());
    }
}































