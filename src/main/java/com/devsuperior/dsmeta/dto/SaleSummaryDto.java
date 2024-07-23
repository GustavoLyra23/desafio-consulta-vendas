package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryProjection;

public class SaleSummaryDto {

    private String sellerName;
    private Double total;

    public SaleSummaryDto() {
    }

    public SaleSummaryDto(SaleSummaryProjection projection) {
        sellerName = projection.getName();
        total = projection.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}


