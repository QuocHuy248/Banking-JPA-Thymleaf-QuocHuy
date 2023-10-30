package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Withdraw;
import com.cg.service.customer.CustomerServiceImpl;
import com.cg.service.customer.ICustomerService;
import com.cg.service.deposit.DepositService;
import com.cg.service.deposit.IDepositService;
import com.cg.service.withdraw.IWithdrawService;
import com.cg.service.withdraw.WithdrawService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/withdraw")
public class WithdrawController {
    private ICustomerService customerService = new CustomerServiceImpl();
    private IWithdrawService withdrawService = new WithdrawService();

    @GetMapping("/create/{id}")
    public String showDeposit(Model model, @PathVariable Long id) {
        Customer customer = customerService.findById(id);
        model.addAttribute("withdraw", new Withdraw(customer));
        return "withdraw/create";
    }

    @PostMapping("/create/{id}")
    public String deposit(Model model, @PathVariable Long id, @ModelAttribute Withdraw withdraw) {
        if (withdraw.getAmount().compareTo(customerService.findById(id).getBalance()) > 1) {
            model.addAttribute("success", false);
            model.addAttribute("message", "Withdraw unsuccessfully");
        } else {
            withdraw.setCustomer(customerService.findById(id));
            withdrawService.create(withdraw);
            model.addAttribute("success", true);
            model.addAttribute("message", "Withdraw successfully");
        }
        model.addAttribute("withdraw", new Withdraw(customerService.findById(id)));
        return "withdraw/create";
    }
}
