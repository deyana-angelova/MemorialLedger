package com.example.MemorialLedger.Order;
import java.util.Date;
import java.util.UUID;

public class Order {
    public UUID Id;
    public String GravestoneId;
    public Status status;
    public String CeramicPhotoId;
    public Float TotalPrice;
    public Date DateOfOrder;
    public Date Deadline;
}
