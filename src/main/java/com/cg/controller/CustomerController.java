package com.cg.controller;

import com.cg.model.Customer;
import com.cg.service.customer.CustomerServiceImpl;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.color.ICC_ColorSpace;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public String showListPage(Model model) {
//        Page<Customer> customers =customerService.findAll(pageable);
        List<Customer> customers = customerService.findAll(false);
        model.addAttribute("customers", customers);

        return "customer/list";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }

    @GetMapping("/banned")
    public String showBanList(Model model) {
        List<Customer> customers = customerService.findAll(true);
        model.addAttribute("customers", customers);
        return "customer/banned-list";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        if (customer.getFullName().length() == 0) {
            model.addAttribute("success", false);
            model.addAttribute("message", "Created unsuccessful");
        } else {
            customerService.create(customer);
            model.addAttribute("success", true);
            model.addAttribute("message", "Created successfully");
        }
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(Model model, @PathVariable Long id) {
        model.addAttribute("customer", customerService.findById(id));
        return "customer/update";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(Model model, @PathVariable Long id, @ModelAttribute Customer customer) {
        customer.setId(id);
        customer.setDeleted(customerService.findById(id).getDeleted());
        customer.setBalance(customerService.findById(id).getBalance());
        customerService.update(customer);
        model.addAttribute("success", true);
        model.addAttribute("message", "Updated successfully");
        return "customer/update";
    }

    @GetMapping("/ban/{id}")
    public String showCustomer(Model model, @PathVariable Long id) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/delete";
    }

    @PostMapping("/ban/{id}")
    public String banCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        customerService.banCustomer(id);
        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Deleted successfully");
        return "redirect:/customers";
    }

    @GetMapping("/unban/{id}")
    public String showBanCustomer(Model model, @PathVariable Long id) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/undelete";
    }

    @PostMapping("/unban/{id}")
    public String unbanCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        customerService.unbanCustomer(id);
        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Unban successfully");
        return "redirect:/customers/banned";
    }
}
