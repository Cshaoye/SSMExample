package com.hufan.service;

import org.apache.ibatis.annotations.Param;

import com.hufan.pojo.User;

/**
 * 用户服务接口
 * 
 * @author hufan
 */
public interface IUserService {

	public int addUser(User user);

	public User getUserById(@Param("id") int id);
	
	public boolean getUserByUserName(User user);

	public void updateUser(User user);

	public int deleteUserById(@Param("id") int id);
}
