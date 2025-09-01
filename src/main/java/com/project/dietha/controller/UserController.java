package com.project.dietha.controller;

import com.project.dietha.entity.User;
import com.project.dietha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UserController {
    private final UserRepository userRepository;

    // CREATE
    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    // READ (모든 유저 조회)
    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // READ (특정 유저 조회)
    @GetMapping("/{id}")
    public User getOne(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
