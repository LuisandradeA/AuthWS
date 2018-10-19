package com.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.repository.UserDAO;

import model.User;

@RestController
@RequestMapping("/auth")
public class Auth implements IAuth {
	
	private UserDAO db = new UserDAO();
	
	@PostMapping("/cadastrar")
	public String cadastrar(
			@RequestParam("username") String username, 
			@RequestParam("password") String password, 
			@RequestParam("ip") String ip
			) {
				addLog(username, ip, "Cadastro");
		
				if (username != null) {
					if(db.getUser(username)!=null){
						return "Username já cadastrado";
					}
						
					if (passwordIsSecure(password)) {
						User user = new User(username,password);
						db.cadastrar(user);
						
						return "Usuário cadastrado com sucesso"; 
					}
					else {
						return "Sua senha deve ter no mínimo 8 digitos";
					}
				}
				return "Nome de usuário inválido";
	}
	
	@GetMapping("/login")
	public String login(
			@RequestParam("username") String username, 
			@RequestParam("password") String password, 
			@RequestParam("ip") String ip
			) {
				
				addLog(username, ip, "Login");
				
				if (username != null) {
					User user = db.getUser(username);
					if(user!=null) {
						System.out.println(user.getPassword());
						if (user.getPassword().equals(password)) {
							System.out.println(user.getPassword());
							return "Login aceito";
						}
					}
				}
		
		return "Usuário ou senha incorretos";
	}
	
	
	@GetMapping("/users")
	public List<User> showUsers() {
		return db.getAllUsers();
	}
	
	@GetMapping("/users/{username}")
	public String getUser(@PathVariable("username") String username) {
		if (username != null) {
			User user = db.getUser(username);
			if(user!=null) {
				return "Usuário " + user.getUsername() + " já existe";
			}
		}

		return "Usuário não existe";
		
	}
	
	@GetMapping("/teste")
	public String teste() {
		return "Hello World";
	}
	
	
	private void addLog (String username, String ip, String acess_type) {
		db.addLog(ip, username, acess_type);
	}
	
	
	private boolean passwordIsSecure(String username) {
		if (username.length() >= 8) 
			return true;
		else
			return false;
	}
}
	

