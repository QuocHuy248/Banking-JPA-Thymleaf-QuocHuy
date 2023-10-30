package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Transaction;
import com.cg.service.customer.CustomerServiceImpl;
import com.cg.service.customer.ICustomerService;
import com.cg.service.deposit.DepositService;
import com.cg.service.deposit.IDepositService;
import com.cg.service.transaction.ITransactionService;
import com.cg.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ITransactionService transactionService;

    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("transactions", transactionService.findAll());
        return "transaction/list";
    }
    @GetMapping("/create/{id}")
    public String makeTransaction(Model model, @PathVariable Long id) {
        model.addAttribute("recipients", customerService.findAllExceptSender(id));
        model.addAttribute("transfer", new Transaction(customerService.findById(id)));
        return "transaction/create";
    }

    @PostMapping("/create/{id}")
    public String deposit(Model model, @PathVariable Long id, @ModelAttribute Transaction transaction) {
        transaction.setSender(customerService.findById(id));
        transactionService.update(transaction.getRecipient().getId(), transaction);
        if (customerService.findById(id).getBalance().compareTo(transaction.getTransactionAmount()) == 1) {
            transactionService.create(transaction);
            model.addAttribute("transfer", new Transaction(customerService.findById(id)));
            model.addAttribute("success", true);
            model.addAttribute("message", "Transaction successfully");
        } else {
            model.addAttribute("transfer", transaction);
            model.addAttribute("success", false);
            model.addAttribute("message", "Transaction unsuccessfully");
        }
        model.addAttribute("recipients", customerService.findAllExceptSender(id));
        return "transaction/create";
    }
}
