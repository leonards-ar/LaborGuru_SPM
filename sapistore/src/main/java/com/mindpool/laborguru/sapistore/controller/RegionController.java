package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.mapper.dto.RegionDetailsDto;
import com.mindpool.laborguru.sapistore.model.Customer;
import com.mindpool.laborguru.sapistore.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/region")
public class RegionController extends BaseController {

    @Autowired
    RegionRepository regionRepository;

    @RequestMapping("/{customerId}")
    Page<RegionDetailsDto> listByCustomer(@PathVariable Long customerId, Pageable pageable) {

        Customer customer = new Customer();
        customer.setId(customerId);
        Page page = regionRepository.findAllByCustomer(customer, pageable);
        List<RegionDetailsDto> regionDtos = mapper.mapAsList(page.getContent(), RegionDetailsDto.class);
        return new PageImpl<>(regionDtos, pageable, page.getTotalElements());
    }

}
