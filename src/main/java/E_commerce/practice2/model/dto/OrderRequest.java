package E_commerce.practice2.model.dto;

import java.util.List;

public record OrderRequest(
        String customerName,
        String customerEmail,
        List<OrderItemRequest> items
) {
}