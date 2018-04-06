package com.mindpool.laborguru.sapistore.controller;

import com.mindpool.laborguru.sapistore.mapper.dto.CustomerDetailsDto;
import com.mindpool.laborguru.sapistore.mapper.dto.CustomerDto;
import com.mindpool.laborguru.sapistore.model.Customer;
import com.mindpool.laborguru.sapistore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<CustomerDto> list(Pageable pageable) {
        Page<Customer> customers = customerService.list(pageable);

        return new PageImpl<>(mapper.mapAsList(customers.getContent(), CustomerDto.class), pageable, customers.getTotalElements());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CustomerDetailsDto getCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        //CustomerDetailsDto cdto = mapper.map(customer, CustomerDetailsDto.class);
//        List<RegionDto> rdto = mapper.mapAsList(customer.getRegions(), RegionDto.class);
//        CustomerDetailsDto cdetails = new CustomerDetailsDto();
//        cdetails.setCode(customer.getCode());
//        cdetails.setId(customer.getId());
//        cdetails.setName(customer.getName());
//        cdetails.setRegions(rdto);
        return mapper.map(customer, CustomerDetailsDto.class);
    }


}
