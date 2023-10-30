package com.cg.service.transaction;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Transaction;
import com.cg.repository.ITransactionRepository;
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
public class TransactionService implements ITransactionService {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ITransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> findAllExceptSender(Long aLong) {
        return null;
    }

    @Override
    public Transaction findById(Long aLong) {
        return null;
    }

    @Override
    public void create(Transaction transaction) {
        Customer sender = customerService.findById(transaction.getSender().getId());
        Customer recipient = customerService.findById(transaction.getRecipient().getId());;
        transaction.setDateTime(LocalDateTime.now());
        sender.setBalance(sender.getBalance().subtract(transaction.getTransactionAmount()));
        recipient.setBalance(recipient.getBalance().add(transaction.getTransferAmount()));
        customerService.update(sender);
        customerService.update( recipient);
        transactionRepository.save(transaction);
    }

    @Override
    public void update(Long aLong, Transaction transaction) {
        transaction.setFee(Long.parseLong("10"));
        Long fee = transaction.getFee();
        BigDecimal feeAM = BigDecimal.valueOf(fee).
                multiply(transaction.getTransferAmount()).divide(BigDecimal.valueOf(100));
        transaction.setFeeAmount(feeAM);
        transaction.setTransactionAmount(feeAM.add(transaction.getTransferAmount()));
        Customer recipient = customerService.findById(aLong);
        transaction.setRecipient(recipient);
    }

    @Override
    public void removeById(Long aLong) {

    }


}
