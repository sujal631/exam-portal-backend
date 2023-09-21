package com.exam.exam_portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.exam_portal.dao.UserRepository;
import com.exam.exam_portal.entity.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);
        if (user == null) {
            System.out.println("User does not exist with the provided username.");
            throw new UsernameNotFoundException("User does not exist with the provided username.");
        }
        return user;
    }

}
