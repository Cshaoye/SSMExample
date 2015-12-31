package com.hufan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hufan.dao.UserDao;
import com.hufan.pojo.User;
import com.hufan.service.IUserService;

public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteUserById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getUserByUserName(User user) {
		User result = userDao.getUserByUserName(user.getUsername());
		boolean flag = false;
		if(result == null) {
			return flag;
		}
		if(!user.getPassword().equals(result.getPassword())) {
			return flag;
		}
		return true;
	}

}
