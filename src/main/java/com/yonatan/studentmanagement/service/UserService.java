package com.yonatan.studentmanagement.service;

import com.yonatan.studentmanagement.entity.Role;
import com.yonatan.studentmanagement.entity.User;
import com.yonatan.studentmanagement.exception.RoleNotFoundException;
import com.yonatan.studentmanagement.exception.UserNotFoundException;
import com.yonatan.studentmanagement.repository.RoleRepository;
import com.yonatan.studentmanagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user) {

        if (user.getRole() != null) {

            Long roleId = user.getRole().getId();

            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() ->
                            new RoleNotFoundException(
                                    "Role not found with id: " + roleId));

            user.setRole(role);
        }

        // Hash the password before saving it
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id));
    }

    public User updateUser(Long id, User updatedUser) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id));

        existingUser.setUsername(updatedUser.getUsername());

        // Hash the new password before saving it
        existingUser.setPassword(
                passwordEncoder.encode(updatedUser.getPassword())
        );

        if (updatedUser.getRole() != null) {

            Long roleId = updatedUser.getRole().getId();

            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() ->
                            new RoleNotFoundException(
                                    "Role not found with id: " + roleId));

            existingUser.setRole(role);

        } else {
            existingUser.setRole(null);
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id));

        userRepository.delete(existingUser);
    }
}