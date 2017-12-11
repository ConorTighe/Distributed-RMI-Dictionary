package Client;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.rmi.NotBoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/SearchResults") 
public class Results extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Results() {
		super();
		// TODO Auto-generated constructor stub
	}

    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/SearchResults.jsp").forward(
				request, response);
	}
}
