package za.co.digitalplatoon.controller.rest.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class LineItem {

    @Id
    private long id;

    private long quantity;
    private String description;
    private BigDecimal unitPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineItemTotal(){
        BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        return  lineTotal.setScale(2, RoundingMode.HALF_UP);
    }
}
