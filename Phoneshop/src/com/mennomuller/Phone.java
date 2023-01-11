package com.mennomuller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record Phone(int id, String brand, String model, String description,
                    BigDecimal price) {

    public BigDecimal taxFreePrice() {
        return price.divide(new BigDecimal("1.21"), 2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Phone) obj;
        return this.id == that.id &&
                Objects.equals(this.brand, that.brand) &&
                Objects.equals(this.model, that.model) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, description, price);
    }

    @Override
    public String toString() {
        return "Phone[" +
                "id=" + id + ", " +
                "brand=" + brand + ", " +
                "model=" + model + ", " +
                "description=" + description + ", " +
                "price=" + price + ']';
    }

}

