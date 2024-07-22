package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleProjection;

import java.time.LocalDate;

public class SaleDto {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public SaleDto() {
    }

    public SaleDto(SaleProjection projection) {
        id = projection.getId();
        date = projection.getDate();
        amount = projection.getAmount();
        sellerName = projection.getName();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
