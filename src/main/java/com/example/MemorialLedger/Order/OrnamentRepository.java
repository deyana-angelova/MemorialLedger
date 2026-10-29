package com.example.MemorialLedger.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrnamentRepository extends JpaRepository<Ornament, UUID> {
}
