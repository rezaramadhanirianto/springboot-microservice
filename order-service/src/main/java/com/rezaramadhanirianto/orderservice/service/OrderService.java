package com.rezaramadhanirianto.orderservice.service;

import com.rezaramadhanirianto.orderservice.dto.InventoryResponse;
import com.rezaramadhanirianto.orderservice.dto.OrderLineItemDto;
import com.rezaramadhanirianto.orderservice.dto.OrderRequest;
import com.rezaramadhanirianto.orderservice.model.Order;
import com.rezaramadhanirianto.orderservice.model.OrderLineItem;
import com.rezaramadhanirianto.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest){
        var order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemList()
                .stream()
                .map(OrderLineItem::getSkuCode)
                .toList();

        // call inventory service
        // if program is in stock
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block(); // make synchronous

        boolean allProductIsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::getIsInStock);

        if(allProductIsInStock){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product is empty, please try again later");
        }
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDTO) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDTO.getPrice());
        orderLineItem.setQuantity(orderLineItemDTO.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDTO.getSkuCode());
        return orderLineItem;
    }
}
