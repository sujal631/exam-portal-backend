package com.exam.exam_portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exam_portal.entity.Role;
import com.exam.exam_portal.entity.User;
import com.exam.exam_portal.entity.UserRole;
import com.exam.exam_portal.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // create new user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        user.setImageUrl("default.png");
        // encoding password with BCryptPasswordEncoder
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<UserRole> userRoles = new ArrayList<>();

        Role role = new Role(2L, "USER");
        userRoles.add(new UserRole(user, role));

        return userService.createUser(user, userRoles);
    }

    // get user by username
    @GetMapping("/{userName}")
    public ResponseEntity<?> getUser(@PathVariable("userName") String userName) {
        User user = this.userService.getUserByUserName(userName);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.FOUND);
        }
        return new ResponseEntity<String>("No user found with the provided userName.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable("userName") String userName) {
        int userDeleted = this.userService.deleteUserByUserName(userName);
        if (userDeleted == 1) {
            return new ResponseEntity<String>("User deleted successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<String>("No user found with the provided userName.", HttpStatus.NOT_FOUND);
    }

}
