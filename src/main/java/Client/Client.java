package Client;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* This is the servlet handler for the users JSP page input, but the actual client-server interaction is encapsulated in 
 * ClientService */

@WebServlet(urlPatterns = "/SearchMenu")
public class Client extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ClientService ClientServ;
	
    public Client() {
        super();
        this.ClientServ = new ClientService();
    }
    
    // GET JSP Menu view
    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/SearchMenu.jsp").forward(
				request, response);
	}
    
    // POST JSP Menu view
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (request.getParameter("word") != null) {
    		// Store User input
	    	String word = request.getParameter("word");
	        String result;
	        
	        // Pass the lookup request to the RMI Client service
	        result = ClientServ.LookupService(word);
	        request.getSession().setAttribute("result", result);
			response.sendRedirect("/SearchResults"); 
    	}
    	else if (request.getParameter("delete") != null) {
    		// Store User input
    		String word = request.getParameter("delete");
	        String result;
	        
	        // Pass the delete request to the RMI Client service
	        result = ClientServ.DeleteService(word);
	        request.getSession().setAttribute("result", result);
			response.sendRedirect("/SearchResults"); 
    	}
    	else if (request.getParameter("editWord") != null) {
    		// Store User input
    		String word = request.getParameter("editWord");
    		String newWord = request.getParameter("newWord");
	        String result;
	        
	        // Pass the edit word request to the RMI Client service
	        result = ClientServ.WordService(word, newWord);
	        request.getSession().setAttribute("result", result);
			response.sendRedirect("/SearchResults"); 
    	}
    	else if (request.getParameter("editDef") != null) {
    		// Store User input
    		String def = request.getParameter("editDef");
    		String newDef = request.getParameter("newDef");
	        String result;
	        
	        // Pass the edit definition request to the RMI Client service
	        result = ClientServ.DefinitionService(def, newDef);
	        request.getSession().setAttribute("result", result);
			response.sendRedirect("/SearchResults"); 
    	}
    }   
}
