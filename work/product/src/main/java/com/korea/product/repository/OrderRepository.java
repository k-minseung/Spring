package com.korea.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.korea.product.Model.OrderEntity;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
	
	//총 결제금액 -> 상품가격 * 주문개수
	
	@Query("SELECT o.orderId, " //상품번호
			+ "o.product.name, " //상품이름
			+ "o.productCount, " // 주문개수
			+ "o.product.price, "// 삼품가격
			+ "o.orderDate, " // 주문날짜
			+ "(o.product.price * o.productCount) AS totalPrice "//총 결제금액
			+ "FROM OrderEntity o")
	
	List<Object[]> findAllOrderTotalPrices();
	
}
