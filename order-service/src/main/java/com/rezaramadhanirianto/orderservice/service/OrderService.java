package com.rezaramadhanirianto.orderservice.service;

import com.rezaramadhanirianto.orderservice.dto.OrderLineItemDto;
import com.rezaramadhanirianto.orderservice.dto.OrderRequest;
import com.rezaramadhanirianto.orderservice.model.Order;
import com.rezaramadhanirianto.orderservice.model.OrderLineItem;
import com.rezaramadhanirianto.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        var order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDTO) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDTO.getPrice());
        orderLineItem.setQuantity(orderLineItemDTO.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDTO.getSkuCode());
        return orderLineItem;
    }
}
