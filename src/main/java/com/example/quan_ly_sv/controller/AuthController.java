package com.example.quan_ly_sv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.quan_ly_sv.model.Account;
import com.example.quan_ly_sv.repository.AccountRepository;

@Controller
public class AuthController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(Account account) {
        // Mã hóa mật khẩu trước khi lưu vào DB
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return "redirect:/login?success"; // Đăng ký xong chuyển về trang đăng nhập
    }
}