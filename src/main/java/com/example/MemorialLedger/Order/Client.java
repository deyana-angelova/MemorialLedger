package com.example.MemorialLedger.Order;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import com.example.MemorialLedger.Order.Order;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "clients")
public class Client {
    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID Id;
    public String Name;
    public String PhoneNumber;
    @OneToMany(mappedBy = "client")
    public List<Order> orders;
}
