package com.exam.exam_portal.service;

import java.util.List;

import com.exam.exam_portal.entity.User;
import com.exam.exam_portal.entity.UserRole;

public interface UserService {

    // creating user
    public User createUser(User user, List<UserRole> userRoles) throws Exception;

    // get user by userName
    public User getUserByUserName(String userName);

    // delete user by userName
    public int deleteUserByUserName(String userName);
}
