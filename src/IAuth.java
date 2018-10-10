import java.util.List;

public interface IAuth {
	public String cadastrar(String username, String password, String ip);
	public boolean login(String username, String password, String ip);
	public List<String> showUsers();
}
