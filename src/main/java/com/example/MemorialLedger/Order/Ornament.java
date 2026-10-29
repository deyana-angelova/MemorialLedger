package com.example.MemorialLedger.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;
@Entity
public class Ornament {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID Id;
    public String ornamentImageFileName;
    public Integer CatalogueNum;
    public String Name;
    public Float Price;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getOrnamentImageFileName() {
        return ornamentImageFileName;
    }

    public void setOrnamentImageFileName(String ornamentImageFileName) {
        this.ornamentImageFileName = ornamentImageFileName;
    }

    public Integer getCatalogueNum() {
        return CatalogueNum;
    }

    public void setCatalogueNum(Integer catalogueNum) {
        CatalogueNum = catalogueNum;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public String svgFileName;

    public String getSvgFileName() {
        return svgFileName;
    }

    public void setSvgFileName(String svgFileName) {
        this.svgFileName = svgFileName;
    }
}
