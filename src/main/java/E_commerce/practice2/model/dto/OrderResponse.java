package E_commerce.practice2.model.dto;

import java.util.Date;
import java.util.List;

public record OrderResponse(
        Long orderId,
        String customerName,
        String email,
        String status,
        Date orderDate,
        List<OrderItemResponse> items
) {
}
