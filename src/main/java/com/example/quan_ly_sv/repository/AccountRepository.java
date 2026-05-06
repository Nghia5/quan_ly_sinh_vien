package com.example.quan_ly_sv.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quan_ly_sv.model.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByUsername(String username);
}