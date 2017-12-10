package Login;

public class LoginService {

	public boolean isUserValid(String user, String password) {
		if (user.equals("DSProject") && password.equals("password"))
			return true;

		return false;
	}

}