package com.controller;
import java.util.List;

import model.User;


public interface IAuth {
	public String cadastrar(String username, String password, String ip);
	public String login(String username, String password, String ip);
	public String getUser(String username);
	public List<User> showUsers();
}
