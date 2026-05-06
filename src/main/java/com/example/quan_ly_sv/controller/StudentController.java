package com.example.quan_ly_sv.controller;

import java.security.Principal; // Đã thêm thư viện này
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quan_ly_sv.model.Student;
import com.example.quan_ly_sv.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 1. Hiển thị danh sách và Tìm kiếm
    @GetMapping
    public String listStudents(Model model, @RequestParam(required = false) String keyword, Principal principal) {
        
        // Lấy tên tài khoản đang đăng nhập và truyền ra giao diện HTML
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        } else {
            model.addAttribute("username", "Khách");
        }

        // Xử lý tìm kiếm và danh sách
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("students", studentRepository.findByNameContainingIgnoreCase(keyword));
        } else {
            model.addAttribute("students", studentRepository.findAll());
        }
        return "students"; // trả về file students.html
    }

    // 2. Form thêm sinh viên
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form"; 
    }

    // 3. Lưu sinh viên (dùng cho cả Thêm và Sửa)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    // 4. Form sửa sinh viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "student-form";
    }

    // 5. Xóa sinh viên
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") UUID id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
}