package com.example.E_seva_kendra.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_seva_kendra.model.User;
import com.example.E_seva_kendra.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ================= REGISTER =================
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            return "Email already exists";
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public String login(@RequestBody User user){

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){

            User dbUser = existingUser.get();

            if(passwordEncoder.matches(user.getPassword(), dbUser.getPassword())){
                return "Login Successful";
            }
        }

        return "Invalid Email or Password";
    }

    // ================= UPDATE PROFILE =================
    @PutMapping("/update-by-email")
    public String updateProfileByEmail(@RequestBody UserUpdateRequest request) {

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if(optionalUser.isEmpty()) {
            return "User not found with this email";
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            return "Current password is incorrect";
        }

        if (request.getName() != null && !request.getName().isEmpty()) {
            user.setName(request.getName());
        }

        if (request.getNewPassword() != null && !request.getNewPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        userRepository.save(user);

        return "Profile Updated Successfully";
    }

    // ================= GET ALL USERS (ADMIN) =================
    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map(user -> new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),   // fetched from database
                "User"
        )).toList();
    }

    // ================= DTO FOR UPDATE =================
    public static class UserUpdateRequest {

        private String name;
        private String email;
        private String currentPassword;
        private String newPassword;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCurrentPassword() {
            return currentPassword;
        }

        public void setCurrentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }

    // ================= DTO FOR ADMIN USERS =================
    public static class UserDTO {

        private Long id;
        private String name;
        private String email;
        private String mobile;
        private String role;

        public UserDTO(Long id, String name, String email, String mobile, String role) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.role = role;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getMobile() {
            return mobile;
        }

        public String getRole() {
            return role;
        }
    }
}