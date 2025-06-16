package com.bmaignan.apistore.cart.controller;

import com.bmaignan.apistore.cart.model.Cart;
import com.bmaignan.apistore.cart.repository.CartDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartDao cartDao;

    private UUID cartId;
    private UUID unknownId;

    @BeforeEach
    void setUp() {
        cartDao.deleteAll();

        var cart = Cart.builder().build();
        cart = cartDao.save(cart);
        cartId = cart.getId();

        unknownId = UUID.randomUUID();
    }

    @Test
    void getAllCarts_shouldReturnListOfCarts() throws Exception {
        mockMvc.perform(get("/api/carts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(print());
    }

    @Test
    void getCartById_shouldReturnCart() throws Exception {
        mockMvc.perform(get("/api/carts/{id}", cartId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cartId.toString()))
                .andDo(print());
    }

    @Test
    void getCartById_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/carts/{id}", unknownId))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void createCart_shouldCreateTheCart() throws Exception {
        String cartJson = """
                {
                    "cartItems": []
                }
                """;

        mockMvc.perform(post("/api/carts")
                        .contentType("application/json")
                        .content(cartJson))
                .andExpect(status().isOk())
                .andDo(print());

        assertThat(cartDao.findAll()).hasSize(2);
    }

    @Test
    void updateCart_shouldUpdateTheCart() throws Exception {
        String cartJson = """
                {
                    "id": "%s",
                    "cartItems": []
                }
                """.formatted(cartId);

        mockMvc.perform(put("/api/carts/{id}", cartId)
                        .contentType("application/json")
                        .content(cartJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteCart_shouldDeleteTheCart() throws Exception {
        mockMvc.perform(delete("/api/carts/{id}", cartId))
                .andExpect(status().isOk());

        assertThat(cartDao.findAll()).isEmpty();
    }
}
