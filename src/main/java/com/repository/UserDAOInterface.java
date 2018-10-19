package com.repository;

import java.util.List;

import model.User;

public interface UserDAOInterface {
	public void cadastrar(User user);
	public User getUser(String username);
	public List<User> getAllUsers();
	public void addLog(String ip, String username, String acess_type);
}
