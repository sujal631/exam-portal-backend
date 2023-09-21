package com.exam.exam_portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam_portal.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
