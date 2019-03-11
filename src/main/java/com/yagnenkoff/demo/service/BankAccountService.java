package com.yagnenkoff.demo.service;

import com.yagnenkoff.demo.entity.BankAccount;
import com.yagnenkoff.demo.exception.BankTransactionException;
import com.yagnenkoff.demo.exception.NotFoundException;

import java.util.List;

public interface BankAccountService  {
    BankAccount save(BankAccount bankAccount);

    void deleteById(Long id);

    BankAccount findById(Long id) throws NotFoundException;

    void addAmount(Long id, Double amount) throws BankTransactionException;

    void withdrawAmount(Long id, Double amount) throws BankTransactionException;

    List<BankAccount> findByUid(Long uid);
}
