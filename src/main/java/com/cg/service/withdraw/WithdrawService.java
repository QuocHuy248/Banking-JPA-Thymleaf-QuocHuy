package com.cg.service.withdraw;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Withdraw;
import com.cg.repository.IDepositRepository;
import com.cg.repository.IWithdrawRepository;
import com.cg.service.IGeneralService;
import com.cg.service.customer.CustomerServiceImpl;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class WithdrawService implements IWithdrawService {
    @Autowired
    private ICustomerService customerService ;
    @Autowired
    private IWithdrawRepository iWithdrawRepository;



    @Override
    public List<Withdraw> findAll() {
        return null;
    }

    @Override
    public List<Withdraw> findAllExceptSender(Long aLong) {
        return null;
    }

    @Override
    public Withdraw findById(Long aLong) {
        return null;
    }

    @Override
    public void create(Withdraw withdraw) {
        Customer customer = customerService.findById(withdraw.getCustomer().getId());
        withdraw.setDateTime(LocalDateTime.now());
        customer.setBalance(customer.getBalance().subtract(withdraw.getAmount()));
        customerService.update(customer);
        iWithdrawRepository.save(withdraw);
    }

    @Override
    public void update(Long aLong, Withdraw withdraw) {

    }

    @Override
    public void removeById(Long aLong) {

    }
}
