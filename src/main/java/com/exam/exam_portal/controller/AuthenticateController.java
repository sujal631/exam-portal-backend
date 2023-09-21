package com.exam.exam_portal.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exam_portal.config.JwtUtils;
import com.exam.exam_portal.entity.JwtRequest;
import com.exam.exam_portal.entity.JwtResponse;
import com.exam.exam_portal.entity.User;
import com.exam.exam_portal.service.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    // generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User does not exist.");
        }

        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String userName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED" + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID CREDENTIALS" + e.getMessage());
        }
    }

    // return the details of the current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        // type cast from UserDetails to User
        return (User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
    }
}
