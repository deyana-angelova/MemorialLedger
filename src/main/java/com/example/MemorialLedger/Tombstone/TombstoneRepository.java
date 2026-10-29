package com.example.MemorialLedger.Tombstone;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TombstoneRepository extends JpaRepository<Tombstone, UUID> {

}
