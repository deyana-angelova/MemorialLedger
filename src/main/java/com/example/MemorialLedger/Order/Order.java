package com.example.MemorialLedger.Order;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ElementCollection
    private List<String> deceased = new ArrayList<>();

    private String tombstoneId;
    private String cemetery;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String ceramicPhotoId;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfOrder;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date deadline;

    @ManyToMany
    private List<AdditionalService> additionalServices = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<String> getDeceased() { return deceased; }
    public void setDeceased(List<String> deceased) { this.deceased = deceased; }

    public String getTombstoneId() { return tombstoneId; }
    public void setTombstoneId(String tombstoneId) { this.tombstoneId = tombstoneId; }

    public String getCemetery() { return cemetery; }
    public void setCemetery(String cemetery) { this.cemetery = cemetery; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getCeramicPhotoId() { return ceramicPhotoId; }
    public void setCeramicPhotoId(String ceramicPhotoId) { this.ceramicPhotoId = ceramicPhotoId; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public Date getDateOfOrder() { return dateOfOrder; }
    public void setDateOfOrder(Date dateOfOrder) { this.dateOfOrder = dateOfOrder; }

    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) { this.deadline = deadline; }

    public List<AdditionalService> getAdditionalServices() { return additionalServices; }
    public void setAdditionalServices(List<AdditionalService> s) { this.additionalServices = s; }

    //svg design
    @Lob
    private String design;

    public String getDesign() { return design; }
    public void setDesign(String design) { this.design = design; }
}