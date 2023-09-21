package com.exam.exam_portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.exam_portal.dao.RoleRepository;
import com.exam.exam_portal.dao.UserRepository;
import com.exam.exam_portal.entity.User;
import com.exam.exam_portal.entity.UserRole;
import com.exam.exam_portal.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // create user
    @Override
    public User createUser(User user, List<UserRole> userRoles) throws Exception {
        User local = userRepository.findByUserName(user.getUserName());
        if (local != null) {
            System.out.println("User already exists.");
            throw new Exception("User already exists.");
        } else {
            // create user
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = userRepository.save(user);
        }
        return local;
    }

    // get user
    @Override
    public User getUserByUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    // delete user
    @Override
    public int deleteUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return 0;
        }
        userRepository.deleteByUserName(userName);
        return 1;

    }
}
