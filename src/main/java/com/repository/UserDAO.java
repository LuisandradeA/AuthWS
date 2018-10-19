package com.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.User;

public class UserDAO implements UserDAOInterface {
	private Path users_database;
	private Path log_file;
	
	public UserDAO() {
		super();
		//Endereço do log_file
		users_database = Paths.get("/home/giovanni/Documentos/AuthWS_DB/users.txt");
		log_file = Paths.get("/home/giovanni/Documentos/AuthWS_DB/log.txt");
	}
	
	
	public void cadastrar(User user) {
		List<String> line = Arrays.asList(user.getUsername()+"@x@x@"+user.getPassword());
		try {
			Files.write(this.users_database, line,StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public User getUser(String username) {
		try {
			List<String> lines = Files.readAllLines(this.users_database);
			for (String temp : lines) {
				String[] parts = temp.split("@x@x@");
				if (parts[0].equals(username)) {
					User user = new User(parts[0],parts[1]);
					return user;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<User> getAllUsers() {
		List<User> allUsers = null;
		try {
			List<String> lines = Files.readAllLines(this.users_database);
			allUsers = new ArrayList<User>();
			for (String temp : lines) {
				String[] parts = temp.split("@x@x@");
				User user = new User(parts[0],parts[1]);
				allUsers.add(user);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allUsers;
	}

	public void addLog(String ip, String username, String acess_type) {
		List<String> line = Arrays.asList(ip + " _ " + username + " _ " + acess_type);
		try {
			Files.write(this.log_file, line,StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
