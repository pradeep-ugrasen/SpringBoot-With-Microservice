package com.cubisoft.service;

import com.cubisoft.entity.OrderEntity;
import com.cubisoft.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // 👉 Insert 20 test orders at startup
    @PostConstruct
    public void initOrders() {
        if (orderRepository.count() == 0) {

            List<OrderEntity> orders = IntStream.rangeClosed(1, 20)
                    .mapToObj(i -> new OrderEntity(
                            null,
                            "Order-" + i,
                            i * 2,                 // quantity = 2,4,6..40
                            i * 100.50            // price = 100.5, 201, 301.5...
                    ))
                    .collect(Collectors.toList());

            orderRepository.saveAll(orders);

            System.out.println("➡ 20 sample orders inserted successfully!");
        }
    }

    // CRUD Methods
    public OrderEntity saveOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}
