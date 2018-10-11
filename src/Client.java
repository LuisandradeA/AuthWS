import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.ws.rs.*;

public class Client {
	
	public static void main(String[]args) {
		
		while(true){
			System.out.println("--- MENU ---");
			System.out.println("1. Cadastrar");
			System.out.println("2. Login");
			System.out.println("3. Sair");
			
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
						//http://localhost:8080/Auth/auth/cadastro?username=giovannid&password=diajsndijusnd&ip=131.313.312.31
			            URL url = new URL("http://localhost:8080/Auth/auth/cadastro?username=" + usernameCadastro + "&password= " +senhaCadastro +"&ip=131.313.312.31");
			            HttpURLConnection con = (HttpURLConnection) url.openConnection();
			 
			            if (con.getResponseCode() != 200) {
			                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
			            }
			 
			            BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			             
			            System.out.println(br.readLine());
			             
			           
			            con.disconnect();
			 
			        } catch (MalformedURLException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
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
						//http://localhost:8080/Auth/auth/cadastro?username=giovannid&password=diajsndijusnd&ip=131.313.312.31
			            URL url = new URL("http://localhost:8080/Auth/auth/cadastro?username=" + usernameLogin + "&password= " +senhaLogin +"&ip=131.313.312.31");
			            HttpURLConnection con = (HttpURLConnection) url.openConnection();
			 
			            if (con.getResponseCode() != 200) {
			                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
			            }
			 
			            BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			             
			            System.out.println(br.readLine());
			             
			           
			            
			            con.disconnect();
			 
			        } catch (MalformedURLException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
					
					break;
					
				case 3:
					System.out.println("fechando programa...");
					System.exit(1);
					break;
				
				default:
					//TODO
			
			}
						
		}
		
		
	}
}
