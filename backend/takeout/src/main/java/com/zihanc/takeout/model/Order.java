package com.zihanc.takeout.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order_table")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 111L;
    @Id
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_sequence")
    private Long id;

    private Integer status;
//    private Long userId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_id", referencedColumnName = "id")
//    private Employee employee;
    // address id maybe?

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OrderDetails orderDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
//    private LocalDateTime orderTime;
//    private LocalDateTime checkoutTime;
    private Integer payments;
    private BigDecimal totals;

}
