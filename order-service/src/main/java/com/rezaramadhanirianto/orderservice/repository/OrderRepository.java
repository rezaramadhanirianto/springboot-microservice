package com.rezaramadhanirianto.orderservice.repository;

import com.rezaramadhanirianto.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
