package E_commerce.practice2.service;

import E_commerce.practice2.model.Order;
import E_commerce.practice2.model.OrderItem;
import E_commerce.practice2.model.Product;
import E_commerce.practice2.model.dto.OrderItemRequest;
import E_commerce.practice2.model.dto.OrderItemResponse;
import E_commerce.practice2.model.dto.OrderRequest;
import E_commerce.practice2.model.dto.OrderResponse;
import E_commerce.practice2.repository.OrderRepo;
import E_commerce.practice2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final ProductRepository productRepository;

    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.customerEmail());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderList = new ArrayList<>();
        for (OrderItemRequest orders : orderRequest.items()) {
            Product product = productRepository.findById(orders.productId()).orElseThrow(() -> new RuntimeException("Product not found with id: " + orders.productId()));

            product.setStockQuantity(product.getStockQuantity() - orders.quantity());
            productRepository.save(product);

            OrderItem items = OrderItem.builder()
                    .product(product)
                    .quantity(orders.quantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(orders.quantity())))
                    .order(order)
                    .build();

            orderList.add(items);
        }
        order.setOrderItems(orderList);
        Order saved = orderRepo.save(order);

        List<OrderItemResponse> ItemResponsesList = new ArrayList<>();
        for (OrderItem item : saved.getOrderItems()) {
            OrderItemResponse itemResponse = new OrderItemResponse(
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );
            ItemResponsesList.add(itemResponse);
        }

        OrderResponse orderResponse = new OrderResponse(
                saved.getOrderId(),
                saved.getCustomerName(),
                saved.getEmail(),
                saved.getStatus(),
                saved.getOrderDate(),
                ItemResponsesList
        );

        return orderResponse;
    }

    public List<OrderResponse> getAllOrderResponses() {

        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {

            List<OrderItemResponse> ItemResponsesList = new ArrayList<>();

            for (OrderItem item : order.getOrderItems()) {
                OrderItemResponse itemResponse = new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice()
                );
                ItemResponsesList.add(itemResponse);
            }

            OrderResponse orderResponse = new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    ItemResponsesList
            );
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }
}