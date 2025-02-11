package com.bmaignan.apistore.customer.repository;

import com.bmaignan.apistore.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerDao extends JpaRepository<Customer, UUID> {
}
