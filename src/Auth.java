import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/auth")
public class Auth implements IAuth {
	
	private java.nio.file.Path log_file = Paths.get("log.txt");;
	private HashMap<String,User> users = new HashMap();
	
	
	@Path("/cadastrar")
	@POST
	public String cadastrar(
			@QueryParam("username") String username, 
			@QueryParam("password") String password, 
			@QueryParam("ip") String ip
			) {
				addLog(username, ip, "Cadastro");
		
				if (username != null) {
					if (passwordIsSecure(password)) {
						User user = new User(username,password);
						users.putIfAbsent(username, user);
						return "Usuário cadastrado com sucesso"; 
					}
					else {
						return "Sua senha deve ter no mínimo 8 digitos";
					}
				}
				return "Nome de usuário inválido";
	}
	
	@Path("/login")
	@POST
	public boolean login(
			@QueryParam("username") String username, 
			@QueryParam("password") String password, 
			@QueryParam("ip") String ip
			) {
				
				addLog(username, ip, "Login");
				
				if (username != null) {
					if(users.containsKey(username)) {
						if (users.get(username).getPassword() == password) {
							return true;
						}
					}
				}
		
		return false;
	}
	
	
	@GET
	@Path("/users")
	@Produces({MediaType.APPLICATION_JSON })
	public List<String> showUsers() {
		List<String> allUsers = new ArrayList<String>();
		allUsers.addAll(users.keySet());
		
		return allUsers;
	}
	
	@GET
	@Path("/teste")
	@Produces({MediaType.TEXT_PLAIN})
	public String teste() {
		return "Hello World";
	}
	
	
	private void addLog (String username, String ip_adress, String acess_type) {
		List<String> line = Arrays.asList(ip_adress + " _ " + username + " _ " + acess_type);
		try {
			Files.write(this.log_file, line,StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	private boolean passwordIsSecure(String username) {
		if (username.length() >= 8) 
			return true;
		else
			return false;
	}
	
	
	
	
}
	

