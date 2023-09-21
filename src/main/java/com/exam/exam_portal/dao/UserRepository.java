package com.exam.exam_portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam_portal.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserName(String userName);

    public void deleteByUserName(String userName);

}
