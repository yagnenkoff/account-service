package com.yagnenkoff.demo.repository;

import com.yagnenkoff.demo.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Transactional
    void deleteById(Long id);

    List<BankAccount> findByUid(Long uid);
}
