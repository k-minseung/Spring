package com.korea.product.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korea.product.model.ProductEntity;

//인터페이스를 상속받으면 메서드를 자식 인터페이스가 다 쓸 수 있다.
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
//메서드를 구현하지 않고 직접 호출하여 사용할 수 있다.
}
