import java.util.List;

public interface IAuth {
	public void cadastrar(User user, String ip);
	public boolean login(User user, String ip);
	public List<String> showUsers();
}
