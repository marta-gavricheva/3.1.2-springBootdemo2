package com.example.springBootdemo2.dao;

import com.example.springBootdemo2.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleDao {

    List<Role> findAll();

    Role findRoleByAuthority(String authority) throws NoSuchElementException;


}
