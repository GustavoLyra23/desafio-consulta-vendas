package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;
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

}
