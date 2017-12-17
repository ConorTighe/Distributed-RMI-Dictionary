package Client;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.rmi.NotBoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/SearchMenu")
public class Client extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    public Client() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/SearchMenu.jsp").forward(
				request, response);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String word = request.getParameter("word");
        String res;
        System.out.println("The word: "+word);
        
        /*WordWorker wordWork = new WordWorker(word);
        try {
			wordWork.run();
			wordWork.toString();
			res = wordWork.getServerResult();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = "error check console";
		}
        request.getSession().setAttribute("result", res);
		response.sendRedirect("/SearchResults"); */
    }   
}
