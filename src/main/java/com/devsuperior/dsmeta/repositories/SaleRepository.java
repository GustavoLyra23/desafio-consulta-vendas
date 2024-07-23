package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT s.id, s.amount,s.date,se.name from tb_sales as s " +
            "inner join tb_seller as se " +
            "on s.seller_id = se.id " +
            "where s.date between :minDate and :maxDate and UPPER(se.name) like UPPER(CONCAT('%',:name,'%'))",
            countQuery = "SELECT count(*) from tb_sales as s " +
                    "inner join tb_seller as se " +
                    "on s.seller_id = se.id " +
                    "where s.date between :minDate and :maxDate and UPPER(se.name) like UPPER(CONCAT('%',:name,'%'))")
    Page<SaleProjection> searchSaleDto(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);


    @Query(nativeQuery = true, value = "SELECT s.name, CAST(ROUND(SUM(sa.amount), 2) AS DECIMAL(15,2)) AS total " +
            "FROM tb_seller s " +
            "JOIN tb_sales sa ON s.id = sa.seller_id " +
            "WHERE sa.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY s.name",
            countQuery = "SELECT count(*) FROM tb_seller s " +
                    "JOIN tb_sales sa ON s.id = sa.seller_id " +
                    "WHERE sa.date BETWEEN :minDate AND :maxDate " +
                    "GROUP BY s.name")
    Page<SaleSummaryProjection> searchSaleSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
