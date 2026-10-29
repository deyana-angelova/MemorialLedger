package com.example.MemorialLedger.Tombstone;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
@Entity
public class Tombstone {
    public UUID getId() {
        return Id;
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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID Id;
    public Integer CatalogueNum;

    public String Name;

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String imageFileName;
    public Tombstone() {
    }
    @ElementCollection
    private Map<String, BigDecimal> prices = new LinkedHashMap<>();

    public Map<String, BigDecimal> getPrices() { return prices; }
    public void setPrices(Map<String, BigDecimal> prices) { this.prices = prices; }

    public String svgFileName;

    public String getSvgFileName() {
        return svgFileName;
    }

    public void setSvgFileName(String svgFileName) {
        this.svgFileName = svgFileName;
    }
}
