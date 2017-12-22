package Login;

// Validating user login
public class LoginService {

	public boolean isUserValid(String user, String password) {
		// DSProject and password to get past login page and use web app
		if (user.equals("DSProject") && password.equals("password")) {
			return true;
		}else {
			return false;
		}
	}

}