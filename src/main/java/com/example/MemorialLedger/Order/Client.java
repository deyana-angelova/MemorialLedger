package com.example.MemorialLedger.Order;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID Id;
    public String Name;
    public String PhoneNumber;
    public List<UUID> orders = new ArrayList<>();
}
