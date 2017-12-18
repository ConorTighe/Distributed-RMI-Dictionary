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
    	if (request.getParameter("word") != null) {
	    	String word = request.getParameter("word");
	        String res;
	        System.out.println("The word to find: "+word);
	        
	        // Get handle on our pool
	        WorkerPool pool = WorkerPool.getInstance( );
	        // Create Job
	        LookupWorker wordWork = new LookupWorker(word);
	        // Add job to pool
	        res = pool.addJob(wordWork);
	        request.getSession().setAttribute("result", res);
			response.sendRedirect("/SearchResults"); 
    	}
    	else if (request.getParameter("delete") != null) {
    		String word = request.getParameter("delete");
	        String res;
	        System.out.println("The word to delete: "+word);
	        
	        // Get handle on our pool
	        WorkerPool pool = WorkerPool.getInstance( );
	        // Create Job
	        WorkerPlan deleteWork = new DeleteWorker(word);
	        // Add job to pool
	        pool.addJob(deleteWork);
			System.out.println(deleteWork.toString());
			res = deleteWork.getServerResult();
	        request.getSession().setAttribute("result", res);
			response.sendRedirect("/SearchResults"); 
    	}
    }   
}
