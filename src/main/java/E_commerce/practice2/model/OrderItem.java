package E_commerce.practice2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Entity
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class OrderItem {


    @Id
    private int id;

    @ManyToOne
    private Product product;

    private int quantity;
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

}