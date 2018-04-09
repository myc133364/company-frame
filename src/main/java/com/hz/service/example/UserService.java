package com.hz.service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.entity.example.User;
import com.hz.mapper.example.UserMapper;

@Service
public class UserService {
@Autowired
private UserMapper userMapper;
public void saveUser(User user) {
	userMapper.addUser(user);
}
}
