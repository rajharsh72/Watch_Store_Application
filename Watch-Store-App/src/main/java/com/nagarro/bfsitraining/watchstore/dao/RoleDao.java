package com.nagarro.bfsitraining.watchstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.bfsitraining.watchstore.model.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

}
