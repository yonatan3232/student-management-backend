package com.yonatan.studentmanagement.controller;

import com.yonatan.studentmanagement.entity.Role;
import com.yonatan.studentmanagement.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role savedRole = roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {

        return roleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {

        if (!roleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        roleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}