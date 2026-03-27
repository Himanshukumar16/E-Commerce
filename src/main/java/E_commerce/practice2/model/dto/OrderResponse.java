package E_commerce.practice2.model.dto;

import org.springframework.cglib.core.Local;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record OrderResponse(
        String orderId,
        String customerName,
        String email,
        String status,
        LocalDate orderDate,
        List<OrderItemResponse> items
) {
}
