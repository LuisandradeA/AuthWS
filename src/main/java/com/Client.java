package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;


import model.User;

public class Client {
	
	public static void main(String[]args) {
		
		while(true){
			System.out.println("\n \n");
			System.out.println("--- MENU ---");
			System.out.println("1. Cadastrar");
			System.out.println("2. Login");
			System.out.println("3. Usuários cadastrados");
			System.out.println("4. Sair");
			
			Scanner read = new Scanner(System.in);
			
			int option = read.nextInt();
			
			switch(option){
				case 1:
					System.out.println("-> Cadastro");
					Scanner r = new Scanner(System.in);
					
					System.out.println("Digite um username para cadastro:");
					String usernameCadastro = r.nextLine();
					
					System.out.println("Digite uma senha para cadastro:");
					String senhaCadastro = r.nextLine();
					
					try {
						
			            URL url = new URL("http://localhost:8080/auth/cadastrar?username=" + usernameCadastro + "&password=" +senhaCadastro +"&ip=131.313.312.31");
			            HttpURLConnection con = (HttpURLConnection) url.openConnection();
			            con.setRequestMethod("POST");
			            if (con.getResponseCode() != 200) {
			                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
			            }
			 
			            BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			             
			            System.out.println(br.readLine());
			             
			           
			            con.disconnect();
			 
			        } catch (MalformedURLException e) {
			            System.out.println("Não foi possível acessar a API");
			        	//e.printStackTrace();
			        } catch (IOException e) {
			        	System.out.println("Não foi possível acessar a API");
			        	//e.printStackTrace();
			        }
					
					
					break;
				
				case 2: 
					System.out.println("-> Login");
					Scanner re = new Scanner(System.in);
					
					System.out.println("Digite seu username:");
					String usernameLogin = re.nextLine();
					
					System.out.println("Digite sua senha:");
					String senhaLogin = re.nextLine();
				
					try {
						
			            URL url = new URL("http://localhost:8080/auth/login?username=" + usernameLogin + "&password=" +senhaLogin +"&ip=131.313.312.31");
			            
			            HttpURLConnection con = (HttpURLConnection) url.openConnection();
			            
			            if (con.getResponseCode() != 200) {
			                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
			            }
			 
			            BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			             
			            System.out.println(br.readLine());
			             
			            con.disconnect();
			 
			        } catch (MalformedURLException e) {
			            System.out.println("Nao foi possivel acessar a API");
			        	//e.printStackTrace();
			        } catch (IOException e) {
			            //e.printStackTrace();
			        	System.out.println("Nao foi possivel acessar a API");
			        }
					
					break;
					
				case 4:
					System.out.println("Fechando programa...");
					System.exit(1);
					break;
				
				case 3:
					System.out.println("Lista de usuarios cadastrados:");
					try{
						URL url = new URL("http://localhost:8080/auth/users");
			            
			            HttpURLConnection con = (HttpURLConnection) url.openConnection();
			 
			            if (con.getResponseCode() != 200) {
			                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
			            }
			 
			            BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			             
			            Gson gson = new Gson();
			            User[] usuariosArray = gson.fromJson(br.readLine(), User[].class);
			            
			            System.out.println("Lista de usuários: ");
			            for(User user:usuariosArray){
			            	System.out.println("->" + user.getUsername());
			            }
			            
			            //System.out.println(user.getUsername());
			             
			            con.disconnect();
			 
			        } catch (MalformedURLException e) {
			        	System.out.println("Não foi possível acessar a API");
			        	//e.printStackTrace();
			        } catch (IOException e) {
			        	System.out.println("Não foi possível acessar a API");
			        	//e.printStackTrace();
			        }
			
					
				default:
					System.out.println("Selecione uma opção válida");
			
			}
						
		}
		
		
	}
}
