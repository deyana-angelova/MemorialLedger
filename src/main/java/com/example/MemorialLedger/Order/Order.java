package com.example.MemorialLedger.Order;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID Id;
    //public Client Client;
    public List<String> Deceased;
    public String TombstoneId;
    public String Cemetery;
    public Status status;
    public String CeramicPhotoId;
    public Float TotalPrice;
    public Date DateOfOrder;
    public Date Deadline;
}
