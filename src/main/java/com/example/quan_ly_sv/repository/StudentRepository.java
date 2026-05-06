package com.example.quan_ly_sv.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quan_ly_sv.model.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    // Hàm hỗ trợ tìm kiếm theo tên
    List<Student> findByNameContainingIgnoreCase(String name);
}