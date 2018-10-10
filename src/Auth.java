import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/auth")
public class Auth implements IAuth {
	
	private Path log_file;
	private List<User> users = new ArrayList();
	
	
		
	@POST
	public void cadastrar(User user, String ip) {
		
	}

	@POST
	public boolean login(User user, String ip) {
		return false;
	}
	
	
	@GET
	
	public List<String> showUsers() {
		return null;
	}
	
	
	
	
	
	
	
	
}
	

