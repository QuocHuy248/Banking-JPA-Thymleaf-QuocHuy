package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer, Long> {

     Page<Customer> findAll(Pageable pageable);
    List<Customer> findAll(Boolean deleted);
    void banCustomer(Long id);
    void unbanCustomer(Long id);
    void update(Customer customer);
}
