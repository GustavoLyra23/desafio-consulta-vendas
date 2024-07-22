package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleDto;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;


@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    @Transactional(readOnly = true)
    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<SaleDto> findByDateAndName(String minDate, String maxDate, String name, Pageable pageable) {

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate startDate = minDate.isEmpty() ? today.minusYears(2L) : LocalDate.parse(minDate);
        LocalDate endDate = maxDate.isEmpty() ? today : LocalDate.parse(maxDate);

        Page<SaleProjection> projections = repository.searchSaleDto(startDate, endDate, name, pageable);
        return projections.map(SaleDto::new);
    }


}
