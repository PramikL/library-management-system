package com.example.lms.controller;

import com.example.lms.dto.UserRegistrationDTO;
import com.example.lms.entity.Role;
import com.example.lms.entity.User;
import com.example.lms.repo.RoleRepo;
import com.example.lms.repo.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }

        if (userRepo.getUserByUserName(userDTO.getUsername()) != null) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        Role userRole = roleRepo.findByName("USER");

        User newUser = User.builder()
                .username(userDTO.getUsername())
                .fullName(userDTO.getFullName())
                .password(new BCryptPasswordEncoder().encode(userDTO.getPassword()))
                .roles(List.of(userRole))
                .build();
        userRepo.save(newUser);


        return "redirect:/login";

    }
}
