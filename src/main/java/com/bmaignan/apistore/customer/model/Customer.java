package com.bmaignan.apistore.customer.model;

import com.bmaignan.apistore.cart.model.Cart;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    private String firstname;

    private String lastname;

    private String email;

    @OneToOne(mappedBy = "customer")
    private Cart cart;
}
