package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.service.customer.CustomerServiceImpl;
import com.cg.service.customer.ICustomerService;
import com.cg.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IDepositService depositService;

    @GetMapping("/create/{id}")
    public String showDeposit(Model model, @PathVariable Long id) {
        Customer customer = customerService.findById(id);
        model.addAttribute("deposit", new Deposit(customer));
        return "deposit/create";
    }
    @PostMapping("/create/{id}")
    public String deposit(Model model, @PathVariable Long id, @ModelAttribute Deposit deposit) {
        deposit.setCustomer(customerService.findById(id));
        depositService.create(deposit);
        model.addAttribute("deposit", new Deposit(customerService.findById(id)));
        model.addAttribute("success", true);
        model.addAttribute("message", "Deposit successfully");
        return "deposit/create";
    }
}
