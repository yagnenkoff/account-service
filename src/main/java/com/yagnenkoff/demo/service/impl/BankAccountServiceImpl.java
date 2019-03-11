package com.yagnenkoff.demo.service.impl;

import com.yagnenkoff.demo.entity.BankAccount;
import com.yagnenkoff.demo.exception.BankTransactionException;
import com.yagnenkoff.demo.exception.NotFoundException;
import com.yagnenkoff.demo.repository.BankAccountRepository;
import com.yagnenkoff.demo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        bankAccountRepository.deleteById(id);
    }

    @Override
    public BankAccount findById(Long id) throws NotFoundException {
        Optional<BankAccount> customer = bankAccountRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        else{
            throw new NotFoundException("No such account found");
        }
    }

    @Override
    @Transactional
    public void addAmount(Long id, Double amount) throws BankTransactionException {
        if (amount <= 0) {
            throw new BankTransactionException("Incorrect amount input");
        }
        BankAccount account = bankAccountRepository.getOne(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        bankAccountRepository.save(account);
    }

    @Override
    public void withdrawAmount(Long id, Double amount) throws BankTransactionException {
        if (amount <= 0) {
            throw new BankTransactionException("Incorrect amount input");
        }
        BankAccount account = bankAccountRepository.getOne(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        double newBalance = account.getBalance() - amount;
        if (account.getBalance() - amount < 0) {
            throw new BankTransactionException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
        bankAccountRepository.save(account);
    }

    @Override
    public List<BankAccount> findByUid(Long uid) {
        return bankAccountRepository.findByUid(uid);
    }
}
