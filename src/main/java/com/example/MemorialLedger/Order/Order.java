package com.example.MemorialLedger.Order;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID Id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    public Client client;
    @ElementCollection
    public List<String> Deceased;
    public String TombstoneId;
    public String Cemetery;
    public Status status;
    public String CeramicPhotoId;
    public Float TotalPrice;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date DateOfOrder;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date Deadline;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<String> getDeceased() {
        return Deceased;
    }

    public void setDeceased(List<String> deceased) {
        Deceased = deceased;
    }

    public String getTombstoneId() {
        return TombstoneId;
    }

    public void setTombstoneId(String tombstoneId) {
        TombstoneId = tombstoneId;
    }

    public String getCemetery() {
        return Cemetery;
    }

    public void setCemetery(String cemetery) {
        Cemetery = cemetery;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCeramicPhotoId() {
        return CeramicPhotoId;
    }

    public void setCeramicPhotoId(String ceramicPhotoId) {
        CeramicPhotoId = ceramicPhotoId;
    }

    public Float getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        TotalPrice = totalPrice;
    }

    public Date getDateOfOrder() {
        return DateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        DateOfOrder = dateOfOrder;
    }

    public Date getDeadline() {
        return Deadline;
    }

    public void setDeadline(Date deadline) {
        Deadline = deadline;
    }
}
