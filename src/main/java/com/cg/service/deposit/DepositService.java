package com.cg.service.deposit;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Withdraw;
import com.cg.repository.IDepositRepository;
import com.cg.service.customer.CustomerServiceImpl;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class DepositService implements IDepositService {

    @Autowired
    private ICustomerService customerService ;
    @Autowired
    private IDepositRepository depositRepository;



    @Override
    public List<Deposit> findAll() {
        return null;
    }

    @Override
    public List<Deposit> findAllExceptSender(Long aLong) {
        return null;
    }

    @Override
    public Deposit findById(Long aLong) {
        return null;
    }

    @Override
    public void create(Deposit deposit) {
        Customer customer = customerService.findById(deposit.getCustomer().getId());
        deposit.setCustomer(customer);
        deposit.setDateTime(LocalDateTime.now());
        customer.setBalance(customer.getBalance().add(deposit.getAmount()));
        customerService.update(customer);
        depositRepository.save(deposit);
    }

    @Override
    public void update(Long aLong, Deposit deposit) {

    }

    @Override
    public void removeById(Long aLong) {

    }
}
