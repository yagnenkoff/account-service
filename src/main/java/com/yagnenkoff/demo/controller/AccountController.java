package com.yagnenkoff.demo.controller;

import com.yagnenkoff.demo.entity.BankAccount;
import com.yagnenkoff.demo.entity.Customer;
import com.yagnenkoff.demo.exception.BankTransactionException;
import com.yagnenkoff.demo.exception.NotFoundException;
import com.yagnenkoff.demo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class AccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public AccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @ResponseBody
    @RequestMapping(value = "/accounts/create/{uid}", method = RequestMethod.POST)
    public Long createAccount(@PathVariable Long uid) throws NotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject("http://localhost:8080/customers/get/" + uid, Customer.class);
            BankAccount bankAccount = BankAccount.create(uid, 0.0);
            return bankAccountService.save(bankAccount).getId();
        }
        catch (Exception e){
            throw new NotFoundException("User not found");
        }
    }

    @RequestMapping(value = "/accounts/delete/{id}", method = RequestMethod.DELETE)
    public void removeAccount(@PathVariable Long id){
        bankAccountService.deleteById(id);
    }

    @RequestMapping(value = "/accounts/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BankAccount getAccount(@PathVariable Long id) throws NotFoundException {
        return bankAccountService.findById(id);
    }

    @RequestMapping(value = "/accounts/deposit", method = RequestMethod.PUT)
    public void deposit(Long id, Double amount) throws BankTransactionException {
        bankAccountService.addAmount(id, amount);
    }

    @RequestMapping(value = "/accounts/withdraw", method = RequestMethod.PUT)
    public void withDraw(Long id, Double amount) throws BankTransactionException {
        bankAccountService.withdrawAmount(id, amount);
    }

    @RequestMapping(value = "/accounts/get/all/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public List<BankAccount> getByUid(@PathVariable Long uid) throws NotFoundException {
        return bankAccountService.findByUid(uid);
    }
}
