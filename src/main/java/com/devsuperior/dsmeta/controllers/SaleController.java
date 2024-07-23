package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleDto;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDto;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleDto>> getReport(@RequestParam(name = "minDate", defaultValue = "") String minDate,
                                                   @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
                                                   @RequestParam(name = "name", defaultValue = "") String name, Pageable pagable) {
        Page<SaleDto> pageDto = service.findByDateAndName(minDate, maxDate, name, pagable);
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<Page<SaleSummaryDto>> getSummary(@RequestParam(name = "minDate", defaultValue = "") String minDate,
                                                           @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
                                                           Pageable pagable) {
        Page<SaleSummaryDto> pageDto = service.findSaleSummaries(minDate, maxDate, pagable);
        return ResponseEntity.ok(pageDto);
    }
}
