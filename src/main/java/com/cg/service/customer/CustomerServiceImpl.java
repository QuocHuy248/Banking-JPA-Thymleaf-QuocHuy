package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }


    @Override
    public List<Customer> findAllExceptSender(Long aLong) {
        return findAll().stream().filter(customer -> !(customer.getId().equals(aLong))).collect(Collectors.toList());
    }

    @Override
    public Customer findById(Long aLong) {
        return iCustomerRepository.findById(aLong).get();
    }

    @Override
    public void create(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public void update(Long aLong, Customer customer) {
    }

    @Override
    public void removeById(Long aLong) {

    }


    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return iCustomerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> findAll(Boolean deleted) {

        return iCustomerRepository.findAllByDeleted(deleted);
    }

    @Override
    public void banCustomer(Long id) {
        Customer customer= findById(id);
        customer.setDeleted(true);
        iCustomerRepository.save(customer);
    }

    @Override
    public void unbanCustomer(Long id) {
        Customer customer= findById(id);
        customer.setDeleted(false);
        iCustomerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        iCustomerRepository.save(customer);
    }
}
